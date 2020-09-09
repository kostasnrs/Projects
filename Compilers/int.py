#Neiros Konstantinos 2503
#Papagianni Ioanna 2790

import sys

transitionDiagram = [
    [0, 0, 0 , "plustk", "minustk", "multitk", 3, 7, "equaltk", 8, 9, "semicolontk", "commatk", "leftroundbrackettk", "rightroundbrackettk", "leftsquarebrackettk", "rightsquarebrackettk", "eoftk", 1, 2, "error2tk"],
    ["idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", "idtk", 1, 1, "idtk"],
    ["constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", "constanttk", 2, "constanttk"],
    ["dividetk", "dividetk", "dividetk", "dividetk", "dividetk", 4, 6, "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk", "dividetk"],
    [4, 4, 4, 4, 4, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, "error1tk", 4, 4, 4],
    [4, 4, 4, 4, 4, 5, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, "error1tk", 4, 4, 4],
    [6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6],
    ["lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessequaltk", "nonequaltk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk", "lessthantk"],
    ["biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerequaltk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk", "biggerthantk"],
    ["colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "assignmenttk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk", "colontk"]
    ]

word  = ''
state = 0
finalStates = ["idtk", "constanttk", "plustk", "minustk", "multitk", "error1tk", "dividetk", "lessequaltk", "nonequaltk", "lessthantk", "biggerequaltk", "biggerthantk", "assignmenttk", "colontk", "equaltk", "semicolontk", "commatk", "leftroundbrackettk", "rightroundbrackettk", "leftsquarebrackettk", "rightsquarebrackettk", "eoftk", "error2tk"]

charMap = {" ":0, "\t":1, "\n":2, "+":3, "-":4 ,"*":5, "/":6, "<":7, "=":8, ">":9, ":":10, ";":11, ",":12, "(":13, ")":14, "[":15, "]":16, "":17}
letters = [chr(65 + i) for i in range(26)] + [ chr(97+i) for i in range(26)]
digits = [chr(48 + i) for i in range(10)]
statesRet = ["idtk", "constanttk", "lessthantk", "biggerthantk", "colontk", "dividetk"]
bindWords = ["program", "endprogram", "declare", "enddeclare", "if", "then", "else", "endif","while", "endwhile", "repeat","endrepeat","exit", "switch", "case", "endswitch", "forcase","when","endforcase","procedure", "endprocedure", "function", "endfunction", "call", "return", "in","inout","and","or","not","true","false","input","print"]
lines = 1


quadList = []
temp = 1
quadLabel = 0

name = ''
programName = ''
place = ''
assignmentVariable = ''

exitList = []
cList = []

def exitError(msg):
    print lines,":",msg
    exit(0)

def lex():
    global word,state,lines

    word = []
    state = 0
    while state not in finalStates:
        ch = infile.read(1)

        if ch == "\n":
            lines = lines + 1

        word.append(ch)
        if ch in digits:
            ch = 19
        elif ch in letters:
            ch = 18
        elif ch in charMap.keys():
            ch = charMap.get(ch)
        else:
            ch = 20
        state = transitionDiagram[state][ch]

        if state == 0:
            word = []

    if state in statesRet:
        infile.seek(-1, 1)
        if word[len(word)-1] == "\n":
            lines = lines - 1
        word.pop()

    word = "".join(word)

    if state == "error1tk":
        exitError("read eof before end of comments")
    if state == "error2tk":
        exitError("read unrecognized character")

    if word in bindWords:
        state = word + "tk"

    return

if len(sys.argv) != 2:
    print "Give the right arguments"
    exit(0)


def program():
    global word, state, name, programName

    lex()
    if state != "programtk":
        exitError("the first word must be program")
    lex()
    if state != "idtk":
        exitError("the second word must be the program name")

    name = word
    programName = word
    lex()
    block()

    if state != "endprogramtk":
        exitError("last word must be endprogram")
    lex()
    if state != "eoftk":
        exitError("last word must be endprogram")

def block():
    global name, programName

    blockName = name
    declarations()
    subprograms()
    genquad("beginblock", blockName, "_","_")
    statements()
    if blockName == programName:
        genquad("halt", "_", "_","_")
    genquad("endblock", blockName, "_","_")

def declarations():
    global word, state

    if state == "declaretk":
        lex()
        varlist()
        if state != "enddeclaretk":
            exitError("declarations must end with enddeclare")
        lex()

def varlist():
    global word, state, cList

    if state == "idtk":
        cList.append(word)
        lex()
        while state == "commatk":
            lex()
            if state != "idtk":
                exitError("comma must be followed by id")
            cList.append(word)
            lex()

def subprograms():
    global word, state, name
    while state == "proceduretk" or state == "functiontk":
        if state == "proceduretk":
            lex()
            if state != "idtk":
                exitError("procedure must be followed by procedure name")

            name = word

            lex()
            procorfuncbody()

            if state != "endproceduretk":
                exitError("procedure must end with endprocedure")
            lex()
        else:
            lex()
            if state != "idtk":
                exitError("function must be followed by function name")
            name = word

            lex()
            procorfuncbody()

            if state != "endfunctiontk":
                exitError("function must end with endfunction")
            lex()

def procorfuncbody():
    formalpars()
    block()

def formalpars():
    global word, state

    if state != "leftroundbrackettk":
        exitError("after name must be (")
    lex()

    if state != "rightroundbrackettk":
        formalparlist()

    if state != "rightroundbrackettk":
        exitError("after ( or paremeters must be )")
    lex()

def formalparlist():
    global word, state
    formalparitem()

    while state == "commatk":
        lex()
        formalparitem()

def formalparitem():
    global word, state

    if state != "intk" and state !="inouttk":
        exitError("first word of parameter must be in or inout")
    lex()

    if state != "idtk":
        exitError("in or inout must be followed by name")
    lex()

def statements():
    global word, state

    statement()

    while state == "semicolontk":
        lex()
        statement()


def statement():
    global word, state, assignmentVariable
    if state == "idtk":
        assignmentVariable = word
        lex()
        assignmentstat()
    elif state == "iftk":
        lex()
        ifstat()
    elif state == "whiletk":
        lex()
        whilestat()
    elif state == "repeattk":
        lex()
        repeatstat()
    elif state == "exittk":
        lex()
        exitstat()
    elif state == "switchtk":
        lex()
        switchstat()
    elif state == "forcasetk":
        lex()
        forcasestat()
    elif state == "calltk":
        lex()
        callstat()
    elif state == "returntk":
        lex()
        returnstat()
    elif state == "inputtk":
        lex()
        inputstat()
    elif state == "printtk":
        lex()
        printstat()

def assignmentstat():
    global assignmentVariable
    if state != "assignmenttk":
        exitError("variable must be followed by :=")
    lex()
    expression()
    genquad(":=", place, "_", assignmentVariable)

def ifstat():
    global word, state

    conditionTrue, conditionFalse = condition()
    if state != "thentk":
        exitError("condition must be followed by then")

    backpatch(conditionTrue, nextquad())
    lex()
    statements()
    jump = makelist(nextquad())
    genquad("jump","_","_","_")

    backpatch(conditionFalse, nextquad())
    elsepart()
    backpatch(jump, nextquad())

    if state != "endiftk":
        exitError("if must end with endif")

    lex()

def elsepart():
    global word, state

    if state == "elsetk":
        lex()
        statements()

def repeatstat():
    global word, state, exitList

    exitList.append(emptylist())
    start = nextquad()

    statements()

    genquad("jump","_","_",start)
    print exitList
    backpatch(exitList[len(exitList)-1], nextquad())
    exitList = exitList[:len(exitList)-1]
    if state != "endrepeattk":
        exitError("repeat must end with endrepeat")

    lex()

def exitstat():
    global word, state, exitList

    if len(exitList) == 0:
        exitError("exit must be inside enclosing repeat")
    list1 = makelist(nextquad())
    genquad("jump","_","_","_")
    exitList[len(exitList)-1] = merge(exitList[len(exitList)-1], list1)


def whilestat():
    global word, state

    start = nextquad()
    conditionTrue, conditionFalse = condition()

    backpatch(conditionTrue, nextquad())
    statements()
    genquad("jump","_","_",start)
    backpatch(conditionFalse, nextquad())
    if state != "endwhiletk":
        exitError("while must end with endwhile")

    lex()

def switchstat():
    global word, state, place

    jump = emptylist()

    expression()
    expressionPlace1 = place
    if state != "casetk":
        exitError("switch must have at least one case")
    while state == "casetk":
        lex()
        expression()
        expressionPlace2 = place

        cond = makelist(nextquad())
        genquad("<>", expressionPlace1, expressionPlace2, "_")

        if state != "colontk":
            exitError("expression must be followed by :")
        lex()

        statements()
        newJump = makelist(nextquad())
        genquad("jump","_","_","_")
        jump = merge(jump, newJump)

        backpatch(cond, nextquad())


    if state != "endswitchtk":
        exitError("switch must end with endswitch")

    backpatch(jump, nextquad())

    lex()

def forcasestat():
    global word, state

    start = nextquad()
    counter = newtemp()
    genquad(":=", 0, "_", counter)

    if state != "whentk":
        exitError("forcase must have at least one when")
    while state == "whentk":
        lex()
        conditionTrue, conditionFalse = condition()
        if state != "colontk":
            exitError("condition must be followed by :")
        lex()
        backpatch(conditionTrue, nextquad())
        genquad("+",counter,1,counter)

        statements()
        backpatch(conditionFalse, nextquad())

    if state != "endforcasetk":
        exitError("forcase must end with endforcase")

    genquad(">=",counter,1,start)
    lex()

def callstat():
    if state != "idtk":
        exitError("call must be followed by procedure name")
    callName = word
    lex()
    actualpars()
    genquad("call", callName, "_", "_")

def returnstat():
    expression()
    genquad("retv", place, "_", "_")

def printstat():
    expression()
    genquad("out", place, "_", "_")

def inputstat():
    global word, state

    if state != "idtk":
        exitError("input must be followed by variable name")
    genquad("inp", word, "_", "_")
    lex()

def actualpars():
    global word, state

    if state != "leftroundbrackettk":
        exitError("after name must be (")
    lex()

    if state != "rightroundbrackettk":
        actualparlist()

    if state != "rightroundbrackettk":
        exitError("after ( or paremeters must be )")
    lex()

def actualparlist():
    global word, state
    actualparitem()

    while state == "commatk":
        lex()
        actualparitem()

def actualparitem():
    global word, state

    if state != "intk" and state !="inouttk":
        exitError("first word of parameter must be in or inout")

    if state == "intk":
        lex()
        expression()
        genquad("par",place,"CV","_")
    else:
        lex()
        if state != "idtk":
            exitError("inout must be followed by name")
        genquad("par",word,"REF","_")
        lex()

def condition():
    global word, state
    booltermTrue1, booltermFalse1 = boolterm()

    while state == "ortk":
        lex()
        backpatch(booltermFalse1, nextquad())

        booltermTrue2, booltermFalse2 = boolterm()

        booltermTrue1 = merge(booltermTrue1, booltermTrue2)
        booltermFalse1 = booltermFalse2

    return booltermTrue1, booltermFalse1

def boolterm():
    global word, state
    boolfactorTrue1, boolfactorFalse1 = boolfactor()

    while state == "andtk":
        lex()
        backpatch(boolfactorTrue1, nextquad())

        boolfactorTrue2, boolfactorFalse2 = boolfactor()

        boolfactorTrue1 = boolfactorTrue2
        boolfactorFalse1 = merge(boolfactorFalse1, boolfactorFalse2)

    return boolfactorTrue1, boolfactorFalse1

def boolfactor():
    global word, state, place

    true = []
    false = []

    if state == "nottk":
        lex()
        if state != "leftsquarebrackettk":
            exitError("not must be followed by [")
        lex()
        conditionTrue, conditionFalse = condition()
        true = conditionFalse
        false = conditionTrue
        if state != "rightsquarebrackettk":
            exitError("condition must be followed by ]")
        lex()
    elif state == "leftsquarebrackettk":
        lex()
        conditionTrue, conditionFalse = condition()
        true = conditionTrue
        false = conditionFalse
        if state != "rightsquarebrackettk":
            exitError("condition must be followed by ]")
        lex()
    elif state == "falsetk":
        lex()
        true = emptylist()
        false = makelist(nextquad())
        genquad("jump","_","_","_")

    elif state == "truetk":
        lex()
        true = makelist(nextquad())
        genquad("jump","_","_","_")
        false = emptylist()

    else:
        expression()
        expressionPlace1 = place
        relopWord = word
        relationaloper()
        expression()
        expressionPlace2 = place

        true = makelist(nextquad())
        genquad(relopWord, expressionPlace1, expressionPlace2, "_")
        false = makelist(nextquad())
        genquad("jump","_","_","_")

    return true, false

def expression():
    global word, state, place

    optionalsign()
    term()
    termPlace1 = place

    while state == "plustk" or state == "minustk":
        state1 = state
        lex()
        term()
        termPlace2 = place

        if state1 == "plustk":
            w = newtemp()
            genquad("+",termPlace1, termPlace2, w)
            termPlace1 = w
        else:
            w = newtemp()
            genquad("-",termPlace1, termPlace2, w)
            termPlace1 = w
    place = termPlace1

def term():
    global word, state, place

    factor()
    factorPlace1 = place

    while state == "multitk" or state == "dividetk":
        state1 = state
        lex()
        factor()
        factorPlace2 = place
        if state1 == "multitk":
            w = newtemp()
            genquad("*", factorPlace1, factorPlace2, w)
            factorPlace1 = w
        else:
            w = newtemp()
            genquad("/", factorPlace1, factorPlace2, w)
            factorPlace1 = w

    place = factorPlace1

def factor():
    global word, state, place

    if state == "constanttk":
        place = word
        lex()
    elif state == "leftroundbrackettk":
        lex()
        expression()
        if state != "rightroundbrackettk":
            exitError("expression must be followed by )")
        lex()
    elif state == "idtk":
        place = word
        lex()
        idtail()
    else:
        exitError("wrong expression start")

def idtail():
    global word, state, place

    if state == "leftroundbrackettk":
        callName = place

        actualpars();
        w = newtemp()
        genquad("par",w, "RET","_")
        genquad("call",callName,"_","_")
        place = w

def relationaloper():
    global word, state

    if state == "equaltk" or state == "lessequaltk" or state == "nonequaltk" or state == "lessthantk" or state == "biggerequaltk" or state == "biggerthantk":
        lex()
    else:
        exitError("expression must be followed by relational operator")

def optionalsign():
    global word, state

    if state == "plustk" or state == "minustk":
        lex()


def nextquad():
    global quadLabel

    return quadLabel

def newtemp():
    global temp, cList

    tempVariable = "T_"+str(temp)
    temp = temp + 1
    cList.append(tempVariable)
    return tempVariable

def genquad(op, x, y, z):
    global quadList, quadLabel

    quadList.append([quadLabel, op, x, y, z])
    quadLabel = quadLabel + 1

def emptylist():
    return []

def makelist(x):
    return [x]

def merge(l1, l2):
    l3 = []
    l3.extend(l1)
    l3.extend(l2)
    return l3


def backpatch(l1, label):

    for l in l1:
       for q in quadList:
           if q[0] == l:
               q[4] = label
               break

def printInt():
    global outfile

    for q in quadList:
        print q
        outfile.write(str(q)+"\n")

    outfile.close()

def printC():
    global outfile, cList

    line = "#include <stdio.h>\nint main()\n{\n"
    outfile.write(line)
    if len(cList) == 1:
        line = "int " +cList[0] +";\n"
        outfile.write(line)
    elif len(cList) > 1:
        line = "int " +cList[0]

        for i in range(1,len(cList)):
            line = line + "," +cList[i]
        line = line + ";\n"
        outfile.write(line)
    for q in quadList:
        line = "L_"+str(q[0])+": "
        if q[1] == ":=":
            line = line + str(q[4]) +"="+str(q[2])+";"
        elif q[1] == "+" or q[1] == "-" or q[1] == "*" or q[1] == "/" :
            line = line + str(q[4]) +"="+str(q[2])+str(q[1])+str(q[3])+";"
        elif q[1] == "<" or q[1] == "<=" or q[1] == ">" or q[1] == ">=" :
            line = line + "if("+str(q[2])+str(q[1])+str(q[3])+") goto L_"+str(q[4])+";"
        elif q[1] == "=":
            line = line + "if("+str(q[2])+"=="+str(q[3])+") goto L_"+str(q[4])+";"
        elif q[1] == "<>":
            line = line + "if("+str(q[2])+"!="+str(q[3])+") goto L_"+str(q[4])+";"
        elif q[1] == "jump":
            line = line + "goto L_"+str(q[4])+";"
        elif q[1] == "endblock":
            line = line + "{}"
        elif q[1] == "out":
            line = line + "printf(\"%d\\n\","+str(q[2])+");"
        elif q[1] == "inp":
            line = line + "scanf(\"%d\",&"+str(q[2])+");"
        outfile.write(line+"\n")
    outfile.write("}\n")
    outfile.close()
infile = open(sys.argv[1])
list1 = sys.argv[1].split(".")

outfile = open(list1[0]+'.int',"w")

program()
printInt()
outfile = open(list1[0]+'.c',"w")
printC()
print "EEL program compiled successfully"

