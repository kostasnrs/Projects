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
    global word, state

    lex()
    if state != "programtk":
        exitError("the first word must be program")
    lex()
    if state != "idtk":
        exitError("the second word must be the program name")

    lex()
    block()

    if state != "endprogramtk":
        exitError("last word must be endprogram")
    lex()
    if state != "eoftk":
        exitError("last word must be endprogram")

def block():
    declarations()
    subprograms()
    statements()

def declarations():
    global word, state

    if state == "declaretk":
        lex()
        varlist()
        if state != "enddeclaretk":
            exitError("declarations must end with enddeclare")
        lex()

def varlist():
    global word, state

    if state == "idtk":
        lex()
        while state == "commatk":
            lex()
            if state != "idtk":
                exitError("comma must be followed by id")
            lex()

def subprograms():
    global word, state
    while state == "proceduretk" or state == "functiontk":
        if state == "proceduretk":
            lex()
            if state != "idtk":
                exitError("procedure must be followed by procedure name")
            lex()
            procorfuncbody()

            if state != "endproceduretk":
                exitError("procedure must end with endprocedure")
            lex()
        else:
            lex()
            if state != "idtk":
                exitError("function must be followed by function name")
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
    global word, state
    if state == "idtk":
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

    if state != "assignmenttk":
        exitError("variable must be followed by :=")
    lex()
    expression()

def ifstat():
    global word, state

    condition()
    if state != "thentk":
        exitError("condition must be followed by then")
    lex()
    statements()
    elsepart()

    if state != "endiftk":
        exitError("if must end with endif")

    lex()

def elsepart():
    global word, state

    if state == "elsetk":
        lex()
        statements()

def repeatstat():
    global word, state

    statements()

    if state != "endrepeattk":
        exitError("repeat must end with endrepeat")

    lex()

def exitstat():
    global word, state

    lex()

def whilestate():
    global word, state

    condition()
    statements()

    if state != "endwhiletk":
        exitError("while must end with endwhile")

    lex()

def switchstat():
    global word, state

    expression()

    if state != "casetk":
        exitError("switch must have at least one case")
    while state == "casetk":
        lex()
        expression()
        if state != "colontk":
            exitError("expression must be followed by :")
        lex()

        statements()

    if state != "endswitchtk":
        exitError("switch must end with endswitch")
    lex()

def forcasestat():
    global word, state

    if state != "whentk":
        exitError("forcase must have at least one when")
    while state == "whentk":
        lex()
        condition()
        if state != "colontk":
            exitError("condition must be followed by :")
        lex()
        statements()

    if state != "endforcasetk":
        print state
        exitError("forcase must end with endforcase")
    lex()

def callstat():
    if state != "idtk":
        exitError("call must be followed by procedure name")
    actualpars()

def returnstat():
    expression()

def printstat():
    expression()

def inputstat():
    global word, state

    if state != "idtk":
        exitError("input must be followed by variable name")

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
    lex()
    if state == "intk":
        expression()
    else:
        if state != "idtk":
            exitError("inout must be followed by name")
        lex()

def condition():
    global word, state
    boolterm()

    while state == "ortk":
        lex()
        boolterm()

def boolterm():
    global word, state
    boolfactor()

    while state == "andtk":
        lex()
        boolfactor()

def boolfactor():
    global word, state

    if state == "nottk":
        lex()
        if state != "leftsquarebrackettk":
            exitError("not must be followed by [")
        lex()
        condition()
        if state != "rightsquarebrackettk":
            exitError("condition must be followed by ]")
        lex()
    elif state == "leftsquarebrackettk":
        lex()
        condition()
        if state != "rightsquarebrackettk":
            exitError("condition must be followed by ]")
        lex()
    elif state == "falsetk":
        lex()
    elif state == "truetk":
        lex()
    else:
        expression()
        relationaloper()
        expression()
def expression():
    global word, state

    optionalsign()
    term()

    while state == "plustk" or state == "minustk":
        lex()
        term()

def term():
    global word, state

    factor()

    while state == "multitk" or state == "dividetk":
        lex()
        factor()

def factor():
    global word, state

    print state,word
    if state == "constanttk":
        lex()
    elif state == "leftroundbrackettk":
        lex()
        expression()
        if state != "rightroundbrackettk":
            exitError("expression must be followed by )")
        lex()
    elif state == "idtk":
        lex()
        idtail()
    else:
        exitError("wrong expression start")

def idtail():
    global word, state

    if state == "leftroundbrackettk":
        actualpars();

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

infile = open(sys.argv[1])
program()
print "EEL program compiled successfully"
