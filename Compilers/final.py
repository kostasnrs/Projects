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

scopeList = []
scopeLabel = 0

start = 0
end = 0

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
    insertScope(name,'program')
    lex()
    block()

    if state != "endprogramtk":
        exitError("last word must be endprogram")
    lex()
    if state != "eoftk":
        exitError("last word must be endprogram")

def block():
    global name, programName, end, scopeList

    blockName = name

    declarations()
    subprograms()
    if len(scopeList) > 1:
        scopeList[1][2][len(scopeList[1][2])-1][2] = nextquad()

    genquad("beginblock", blockName, "_","_")

    statements()
    if blockName == programName:
        genquad("halt", "_", "_","_")

    genquad("endblock", blockName, "_","_")
    if len(scopeList) > 1:
        scopeList[1][2][len(scopeList[1][2])-1][3] = findOffset()
    end = quadLabel
    convertIntToAssembly()
    deleteScope()


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

        offset = findOffset()
        entity = [word, offset]
        insertEntity(entity)

        lex()
        while state == "commatk":
            lex()
            if state != "idtk":
                exitError("comma must be followed by id")
            cList.append(word)

            offset = findOffset()
            entity = [word, offset]
            insertEntity(entity)

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

            insertScope(name,'procedure')

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

            insertScope(name,'function')

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
    if state == "intk":
        parameterType = "CV"
    else:
        parameterType = "REF"
    lex()

    if state != "idtk":
        exitError("in or inout must be followed by name")

    offset = findOffset()
    entity = [word, offset, parameterType]
    insertEntity(entity)
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

    entity = locateEntity(assignmentVariable)
    if len(entity)>3:
        exitError("incorrect type of variable")

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
    entity = locateEntity(callName)
    if len(entity)<=3:
        exitError("incorrect type of function")
    if entity[1] != 'procedure':
        exitError("you have called a function")
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
    locateEntity(word)
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
        locateEntity(word)
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
    elif state == "truetk":
        lex()
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

        entity = locateEntity(callName)
        if len(entity)<=3:
            exitError("incorrect type of function")
        if entity[1] != 'function':
            exitError("you have called a procedure")

        actualpars()
        w = newtemp()
        genquad("par",w, "RET","_")
        genquad("call",callName,"_","_")
        place = w
    else:
        entity = locateEntity(place)
        if len(entity)>3:
            exitError("incorrect type of variable")

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

    offset = findOffset()
    entity = [tempVariable, offset]
    insertEntity(entity)

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

def insertScope(scopeName,type):
    global scopeList, scopeLabel

    entity = [scopeName, type,-1,-1]
    if scopeLabel > 0:
        scopeList[0][2].extend([entity])
    print entity
    scope = [[scopeLabel, scopeName, []]]
    scope.extend(scopeList)
    scopeList = scope
    scopeLabel = scopeLabel + 1

def deleteScope():
    global scopeList, scopeLabel

    scopeLabel = scopeLabel - 1
    printScopeList()
    scopeList.pop(0)


def printScopeList():
    global scopeList

    for scope in scopeList:
        print scope

def findEntity(entityName):
    global scopeList

    for entity in scopeList[0][2]:
        if entityName == entity[0]:
            printScopeList()
            exitError(entityName + " already defined")


def insertEntity(entity):
    global scopeList
    findEntity(entity[0])
    scopeList[0][2].append(entity)

def findOffset():
    global scopeList

    offset = 12
    for entity in scopeList[0][2]:
        if len(entity)<4:
            offset = entity[1] + 4
    return offset


def locateEntity(entityName):
    global scopeList

    for scope in scopeList:
        for entity in scope[2]:
            if entityName == entity[0]:
                return entity
    exitError("entity not found")

def findEntityAssembly(entityName):
    global scopeList


    for scope in scopeList:
        for entity in scope[2]:
            if entityName == entity[0]:
                if len(entity) != 3:
                    return entity[1], scope[0], False
                if entity[2] == 'REF':
                    return entity[1], scope[0], True
                return entity[1], scope[0], False


def gnlvcode(offset, entityLabel):
    global outfileAssembly, scopeLabel

    outfileAssembly.write('lw $t0, -4($sp)\n')
    for i in range(scopeLabel - entityLabel -1):
        outfileAssembly.write('lw $t0, -4($t0)\n')
    outfileAssembly.write('add $t0, $t0, -'+str(offset) +'\n')

def loadvr(v,r):
    global scopeLabel
    v = str(v)
    if v[0] in digits:
        outfileAssembly.write('li $t'+str(r)+', ' +v+'\n')
    else:
        offset, entityLabel, isRef = findEntityAssembly(v)
        if entityLabel == 0:
            outfileAssembly.write('lw $t'+str(r)+',-'+str(offset) +'($s0)\n')
        elif entityLabel == scopeLabel-1:
            if isRef == False:
                outfileAssembly.write('lw $t'+str(r)+',-'+str(offset) +'($sp)\n')
            else:
                outfileAssembly.write('lw $t0,-'+str(offset) +'($sp)\n')
                outfileAssembly.write('lw $t'+str(r)+',($t0)\n')
        else:
            if isRef == False:
                gnlvcode(offset, entityLabel)
                outfileAssembly.write('lw $t'+str(r)+',($t0)\n')
            else:
                gnlvcode(offset, entityLabel)
                outfileAssembly.write('lw $t0,($t0)\n')
                outfileAssembly.write('lw $t'+str(r)+',($t0)\n')

def storerv(r,v):
    global scopeLabel

    offset, entityLabel, isRef = findEntityAssembly(v)
    if entityLabel == 0:
        outfileAssembly.write('sw $t'+str(r)+',-'+str(offset) +'($s0)\n')
    elif entityLabel == scopeLabel-1:
        if isRef == False:
            outfileAssembly.write('sw $t'+str(r)+',-'+str(offset) +'($sp)\n')
        else:
            outfileAssembly.write('lw $t0,-'+str(offset) +'($sp)\n')
            outfileAssembly.write('sw $t'+str(r)+',($t0)\n')
    else:
        if isRef == False:
            gnlvcode(offset, entityLabel)
            outfileAssembly.write('lw $t'+str(r)+',($t0)\n')
        else:
            gnlvcode(offset, entityLabel)
            outfileAssembly.write('lw $t0,($t0)\n')
            outfileAssembly.write('sw $t'+str(r)+',($t0)\n')

def areSiblings(f1, f2):
    global scopeList

    if len(scopeList) ==1 :
        return False
    foundF1 = False
    foundF2 = False
    for entity in scopeList[1][2]:
        if f1 == entity[0]:
            foundF1 = True
        if f2 == entity[0]:
            foundF2 = True
    if foundF1 == True and foundF2 == True:
        return True
    return False

def convertIntToAssembly():
    global outfileAssembly, start, end, quadList, scopeLabel, scopeList

    paramNum = 0
    print start, end
    for i in range(start, end):
        q = quadList[i]

        outfileAssembly.write("L_"+str(q[0])+": \n")
        if q[1] == ":=":
            loadvr(q[2],1)
            storerv(1,q[4])
        elif q[1] == "+" or q[1] == "-" or q[1] == "*" or q[1] == "/" :
            loadvr(q[2],1)
            loadvr(q[3],2)
            if q[1] == "+":
                outfileAssembly.write("add $t1, $t1, $t2\n")
            elif q[1] == "-":
                outfileAssembly.write("sub $t1, $t1, $t2\n")
            elif q[1] == "*":
                outfileAssembly.write("mul $t1, $t1, $t2\n")
            else:
                outfileAssembly.write("div $t1, $t1, $t2\n")

            storerv(1,q[4])
        elif q[1] == "<" or q[1] == "<=" or q[1] == ">" or q[1] == ">=" :
            loadvr(q[2],1)
            loadvr(q[3],2)
            if q[1] == "<":
                outfileAssembly.write("blt $t1, $t2, L_"+str(q[4])+"\n")
            elif q[1] == "<=":
                outfileAssembly.write("ble $t1, $t2, L_"+str(q[4])+"\n")
            elif q[1] == ">":
                outfileAssembly.write("bgt $t1, $t2, L_"+str(q[4])+"\n")
            elif q[1] == ">=":
                outfileAssembly.write("bge $t1, $t2, L_"+str(q[4])+"\n")
            elif q[1] == "=":
                outfileAssembly.write("beq $t1, $t2, L_"+str(q[4])+"\n")
            elif q[1] == "<>":
                outfileAssembly.write("bne $t1, $t2, L_"+str(q[4])+"\n")
        elif q[1] == "jump":
            outfileAssembly.write("j L_"+str(q[4])+"\n")
        elif q[1] == "endblock":
            if scopeLabel > 1:
                outfileAssembly.write("lw $ra, ($sp)\n")
                outfileAssembly.write("jr $ra\n")
        elif q[1] == "beginblock":
            if scopeLabel > 1:
                outfileAssembly.write("sw $ra, ($sp)\n")
            else:
                outfileAssembly.write("L_main:\n")
                frameLength = findOffset()
                outfileAssembly.write("add $sp, $sp, " + str(frameLength)+"\n")
                outfileAssembly.write("move $s0, $sp\n")
        elif q[1] == "out":
            outfileAssembly.write("li $v0, 1\n")
            loadvr(q[2], 0)
            outfileAssembly.write("move $a0, $t0\n")
            outfileAssembly.write("syscall\n")
        elif q[1] == "inp":
            outfileAssembly.write("li $v0, 5\n")
            outfileAssembly.write("syscall\n")
            outfileAssembly.write("move $t0, $v0\n")
            storerv(0, q[2])
        elif q[1] == "retv":
            loadvr(q[2],1)
            outfileAssembly.write("lw $t0, -8($sp)\n")
            outfileAssembly.write("sw $t1, ($t0)\n")
        elif q[1] == 'par':
            if paramNum == 0:
                frameLength = 0
                for j in range(i, len(quadList)):
                    if quadList[j][1] == 'call':
                        entity = locateEntity(quadList[j][2])
                        frameLength = entity[3]
                        break
                outfileAssembly.write("add $fp, $sp, " + str(frameLength)+"\n")

            if q[3] == 'CV':
                loadvr(q[2], 0)
                offset = -(12 + 4*paramNum)
                outfileAssembly.write("sw $t0, -" + str(offset)+"($fp)\n")
            elif q[3] == 'RET':
                entity = locateEntity(q[2])
                outfileAssembly.write("add $t0, $sp, -"+str(entity[1])+"\n")
                outfileAssembly.write("sw $t0, -8($fp)\n")
            elif q[3] == 'REF':
                offset, entityLabel, isRef = findEntityAssembly(q[2])
                if entityLabel == scopeLabel -1:
                    if isRef == True:
                        outfileAssembly.write("lw $t0, -" + str(offset)+"($sp)\n")
                        offset = -(12 + 4*paramNum)
                        outfileAssembly.write("sw $t0, -" + str(offset)+"($fp)\n")
                    else:
                        outfileAssembly.write("add $t0, $sp, -" + str(offset)+"\n")
                        offset = -(12 + 4*paramNum)
                        outfileAssembly.write("sw $t0, -" + str(offset)+"($fp)\n")
                else:
                    if isRef == True:
                        gnlvcode(q[2],entityLabel)
                        outfileAssembly.write("lw $t0, ($t0)\n")
                        offset = -(12 + 4*paramNum)
                        outfileAssembly.write("sw $t0, -" + str(offset)+"($fp)\n")
                    else:
                        gnlvcode(q[2],entityLabel)
                        offset = -(12 + 4*paramNum)
                        outfileAssembly.write("sw $t0, -" + str(offset)+"($fp)\n")

            paramNum = paramNum + 1
        elif q[1] == 'call':
            paramNum = 0
            entity = locateEntity(q[2])
            if areSiblings(q[2], scopeList[0][1]) == True:
                outfileAssembly.write("sw $t0, -4($sp)\n")
                outfileAssembly.write("sw $t0, -4($fp)\n")
            else:
                start = 0
            outfileAssembly.write("sw $sp, -4($fp)\n")
            outfileAssembly.write("add $sp, $sp, "+str(entity[3])+"\n")
            outfileAssembly.write("jal L_"+str(entity[2])+"\n")
            outfileAssembly.write("add $sp, $sp, -"+str(entity[3])+"\n")

    start = end


start = 0
end = 0
infile = open(sys.argv[1])
list1 = sys.argv[1].split(".")

outfile = open(list1[0]+'.int',"w")
outfileAssembly = open(list1[0]+'.asm',"w")
outfileAssembly.write("j L_main\n")
program()
printInt()
outfile = open(list1[0]+'.c',"w")
printC()


printScopeList()
print "EEL program compiled successfully"

