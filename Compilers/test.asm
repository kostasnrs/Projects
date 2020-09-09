j L_main
L_0: 
sw $ra, ($sp)
L_1: 
lw $t1,-12($sp)
lw $t0, -8($sp)
sw $t1, ($t0)
L_2: 
lw $ra, ($sp)
jr $ra
L_3: 
sw $ra, ($sp)
L_4: 
lw $t1,-24($sp)
li $t2, 4
blt $t1, $t2, L_6
L_5: 
j L_12
L_6: 
li $t1, 0
lw $t0, -4($sp)
lw $t0, -4($t0)
add $t0, $t0, -32
lw $t1,($t0)
L_7: 
li $t1, 1
sw $t1,-20($sp)
L_8: 
li $t1, 4
lw $t2,-28($sp)
add $t1, $t1, $t2
sw $t1,-40($sp)
L_9: 
lw $t1,-40($sp)
sw $t1,-12($sp)
L_10: 
lw $t1,-12($sp)
sw $t1,-24($sp)
L_11: 
j L_4
L_12: 
li $t1, 4
lw $t0, -4($sp)
lw $t0, -4($t0)
add $t0, $t0, -28
lw $t2,($t0)
div $t1, $t1, $t2
sw $t1,-44($sp)
L_13: 
li $t1, 2
lw $t2,-44($sp)
add $t1, $t1, $t2
sw $t1,-48($sp)
L_14: 
lw $t1,-12($sp)
li $t2, 7
mul $t1, $t1, $t2
sw $t1,-52($sp)
L_15: 
lw $t1,-48($sp)
lw $t2,-52($sp)
sub $t1, $t1, $t2
sw $t1,-56($sp)
L_16: 
lw $t1,-56($sp)
sw $t1,-20($sp)
L_17: 
j L_17
L_18: 
add $fp, $sp, 20
lw $t0,-32($sp)
sw $t0, --12($fp)
L_19: 
add $t0, $sp, -60
sw $t0, -8($fp)
L_20: 
sw $t0, -4($sp)
sw $t0, -4($fp)
sw $sp, -4($fp)
add $sp, $sp, 20
jal L_0
add $sp, $sp, -20
L_21: 
li $t1, 3
lw $t2,-60($sp)
mul $t1, $t1, $t2
sw $t1,-64($sp)
L_22: 
lw $t1,-20($sp)
lw $t2,-64($sp)
add $t1, $t1, $t2
sw $t1,-68($sp)
L_23: 
L_24: 
j L_27
L_25: 
lw $t1,-24($sp)
li $t2, 0
blt $t1, $t2, L_29
L_26: 
j L_27
L_27: 
lw $t0, -4($sp)
lw $t0, -4($t0)
add $t0, $t0, -24
lw $t1,($t0)
li $t2, 0
bge $t1, $t2, L_29
L_28: 
j L_29
L_29: 
j L_30
L_30: 
lw $t1,-20($sp)
lw $t2,-16($s0)
bgt $t1, $t2, L_32
L_31: 
j L_34
L_32: 
li $t1, 90
sw $t1,-16($s0)
L_33: 
j L_35
L_34: 
li $t1, 100
sw $t1,-36($sp)
L_35: 
li $t1, 0
sw $t1,-72($sp)
L_36: 
lw $t1,-20($sp)
lw $t2,-16($s0)
bgt $t1, $t2, L_38
L_37: 
j L_44
L_38: 
lw $t1,-72($sp)
li $t2, 1
add $t1, $t1, $t2
sw $t1,-72($sp)
L_39: 
li $t1, 4
lw $t0, -4($sp)
lw $t0, -4($t0)
add $t0, $t0, -28
lw $t2,($t0)
div $t1, $t1, $t2
sw $t1,-76($sp)
L_40: 
li $t1, 2
lw $t2,-76($sp)
add $t1, $t1, $t2
sw $t1,-80($sp)
L_41: 
lw $t1,-12($sp)
li $t2, 7
mul $t1, $t1, $t2
sw $t1,-84($sp)
L_42: 
lw $t1,-80($sp)
lw $t2,-84($sp)
sub $t1, $t1, $t2
sw $t1,-88($sp)
L_43: 
lw $t1,-88($sp)
sw $t1,-20($sp)
L_44: 
lw $t1,-20($sp)
lw $t2,-16($s0)
bge $t1, $t2, L_46
L_45: 
j L_49
L_46: 
lw $t1,-72($sp)
li $t2, 1
add $t1, $t1, $t2
sw $t1,-72($sp)
L_47: 
li $t1, 3
li $t2, 4
add $t1, $t1, $t2
sw $t1,-92($sp)
L_48: 
lw $t1,-92($sp)
sw $t1,-16($s0)
L_49: 
lw $t1,-72($sp)
li $t2, 1
bge $t1, $t2, L_35
L_50: 
L_51: 
li $t1, 9
sw $t1,-16($sp)
L_52: 
j L_56
L_53: 
L_54: 
li $t1, 10
sw $t1,-16($sp)
L_55: 
j L_56
L_56: 
lw $t1,-24($sp)
lw $t0, -8($sp)
sw $t1, ($t0)
L_57: 
lw $ra, ($sp)
jr $ra
L_58: 
sw $ra, ($sp)
L_59: 
li $v0, 5
syscall
move $t0, $v0
sw $t0,-36($sp)
L_60: 
lw $t1,-36($sp)
li $t2, 2
add $t1, $t1, $t2
sw $t1,-40($sp)
L_61: 
li $v0, 1
lw $t0,-40($sp)
move $a0, $t0
syscall
L_62: 
lw $t1,-36($sp)
li $t2, 2
bgt $t1, $t2, L_64
L_63: 
j L_67
L_64: 
j L_77
L_65: 
li $t1, 0
sw $t1,-28($s0)
L_66: 
j L_68
L_67: 
li $t1, 3
sw $t1,-36($sp)
L_68: 
lw $t1,-36($sp)
li $t2, 2
blt $t1, $t2, L_70
L_69: 
j L_76
L_70: 
lw $t1,-36($sp)
li $t2, 1
add $t1, $t1, $t2
sw $t1,-44($sp)
L_71: 
lw $t1,-44($sp)
sw $t1,-36($sp)
L_72: 
j L_74
L_73: 
j L_72
L_74: 
li $t1, 0
sw $t1,-12($sp)
L_75: 
j L_68
L_76: 
j L_62
L_77: 
lw $ra, ($sp)
jr $ra
L_78: 
sw $ra, ($sp)
L_79: 
lw $t1,-24($sp)
lw $t0, -8($sp)
sw $t1, ($t0)
L_80: 
lw $ra, ($sp)
jr $ra
L_81: 
L_main:
add $sp, $sp, 32
move $s0, $sp
L_82: 
add $fp, $sp, 48
lw $t0,-20($s0)
sw $t0, --12($fp)
L_83: 
add $t0, $sp, -24
sw $t0, --16($fp)
L_84: 
sw $sp, -4($fp)
add $sp, $sp, 48
jal L_58
add $sp, $sp, -48
L_85: 
li $v0, 5
syscall
move $t0, $v0
sw $t0,-20($s0)
L_86: 
li $t1, 0
sw $t1,-12($s0)
L_87: 
L_88: 
