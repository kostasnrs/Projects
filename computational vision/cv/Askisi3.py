import numpy as np
import sys
import math
import matplotlib.pyplot as plt
import cv2
from PIL import Image, ImageDraw
from scipy import signal
from scipy import misc
from scipy import ndimage

def _radius(x, y, i, j):
	return ((x - x0[i])**2 + (y - y0[j])**2)**0.5

if __name__=="__main__":

	inputImage = sys.argv[1]
	threshold = 0.2
	openedImage = Image.open(str(inputImage))
	grayImage = np.average(openedImage, axis=-1) 
	print("Image loading...")

	img = cv2.imread(inputImage,0)
	img = cv2.medianBlur(img,5)
	cimg = cv2.cvtColor(img,cv2.COLOR_GRAY2BGR)

	#############################################
	####		SOBEL ALGORITHM				#####
	#############################################
	print("Image loaded!\nStarting sobel...")

	width, height = openedImage.size
	newimg = Image.new("RGB", (width, height), "white")
	for x in range(1, width-1):
		for y in range(1, height-1):
			Gx = 0
			Gy = 0
			p = openedImage.getpixel((x-1, y-1))
			red = p[0]
			green = p[1]
			blue = p[2]
			intensity = red + green + blue

			Gx += -intensity
			Gy += -intensity
			p = openedImage.getpixel((x-1, y))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gx += -2 * (red + green + blue)
			p = openedImage.getpixel((x-1, y+1))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gx += -(red + green + blue)
			Gy += (red + green + blue)
			p = openedImage.getpixel((x, y-1))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gy += -2 * (red + green + blue)
			p = openedImage.getpixel((x, y+1))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gy += 2 * (red + green + blue)
			p = openedImage.getpixel((x+1, y-1))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gx += (red + green + blue)
			Gy += -(red + green + blue)
			p = openedImage.getpixel((x+1, y))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gx += 2 * (red + green + blue)
			p = openedImage.getpixel((x+1, y+1))
			red = p[0]
			green = p[1]
			blue = p[2]

			Gx += (red + green + blue)
			Gy += (red + green + blue)
			length = math.sqrt((Gx * Gx) + (Gy * Gy))
			length = length / 4328 * 255
			length = int(length)
			newimg.putpixel((x,y),(length,length,length))

	X = np.array([[-1,0,1],[-2,0,2],[-1,0,1]])
	Y = np.array([[-1,-2,-1],[0,0,0],[1,2,1]])

	Gx=signal.convolve2d(grayImage,X,"same","symm") 
	Gy=signal.convolve2d(grayImage,Y,"same","symm")
	result = np.abs(Gx)+np.abs(Gy)
	valueMax = np.max(result) 
	width2,height2=result.shape

	for i in range (width2):
		for j in range (height2):
			if (result[i,j] > valueMax*threshold):
				result[i,j]=255
			else:
				result[i,j] = 0

	#############################################
	####		HOUGH ALGORITHM				#####
	#############################################

	print("Sobel algirithm finished!\nStarting Hough...")

	circles = np.zeros((15, 15, 5))
	x0 = np.linspace(0, width, 15).astype(int)
	y0 = np.linspace(0, height, 15).astype(int)
	r = np.linspace(10, 20, 5).astype(int)
	
	for i in range(0, 15):
		print(i+1,"out of 15 x0 checked")
		for j in range(0, 15):
			for w in range(0, width):
				for h in range(0, height):
					# if 10 <= _radius(w, h, i, j) <= 20:
					# 	circles[i][j] += 1
					b = _radius(w, h, i, j)
					if b in r:
						if result[h][w] == 255:
							circles[i][j][np.where(r == b)[0]] += 1
						#print(np.where(r == b)[0][0])
	
	circles = circles.astype(int)
	draw = ImageDraw.Draw(openedImage)
	print("Hough algirithm finished!\nStart drawing...")
	
	#############################################
	####		DRAWING IN IMAGE			#####
	#############################################

	for i in range(0, 15):
		for j in range(0, 15):
			for k in range(0, 5):
				#draw.arc([x0[i]+r[k], y0[j]+r[k], x0[i]-r[k], y0[j]-r[k]], r[k], r[k], fill=255)
				if(circles[i][j][k] > 5):
					cv2.circle(cimg,(x0[i], y0[j]), r[k], (0,255,0),2)
		#print(i)
	# plt.imshow(openedImage, cmap="gray")
	# plt.show()
	print("Drawing finished!\nShowing results...")
	print(circles)
	cv2.imshow('detected circles', cimg)
	cv2.waitKey(0)
	cv2.destroyAllWindows()
