import numpy as np 
import sys
import math
import matplotlib.pyplot as plt
from scipy import misc
from PIL import Image
from scipy import signal 

if __name__ =="__main__": 

    inputImage = sys.argv[1] 
    threshold = float(sys.argv[2])

    openedImage = Image.open(str(inputImage))
    grayImage = np.average(openedImage, axis=-1) 
    plt.imshow(grayImage, interpolation='nearest', cmap=plt.get_cmap('gray'))
    plt.show() 
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

    plt.imshow(result, cmap ="gray")
    plt.show()  
    print(result)