j L_main
L_0: 
sw $ra, ($sp)
L_1: 
li $v0, 5
syscall
move $t0, $v0
sw $t0,-12($sp)
L_2: 
li $v0, 1
lw $t0,-12($sp)
move $a0, $t0
syscall
L_3: 
lw $t1,-12($sp)
lw $t0, -8($sp)
sw $t1, ($t0)
L_4: 
lw $ra, ($sp)
jr $ra
L_5: 
L_main:
add $sp, $sp, 32
move $s0, $sp
L_6: 
add $fp, $sp, 16
add $t0, $sp, -20
sw $t0, -8($fp)
L_7: 
sw $sp, -4($fp)
add $sp, $sp, 16
jal L_0
add $sp, $sp, -16
L_8: 
lw $t1,-20($s0)
sw $t1,-12($s0)
L_9: 
lw $t1,-12($s0)
li $t2, 0
bgt $t1, $t2, L_11
L_10: 
j L_15
L_11: 
li $v0, 1
lw $t0,-12($s0)
move $a0, $t0
syscall
L_12: 
lw $t1,-12($s0)
li $t2, 2
sub $t1, $t1, $t2
sw $t1,-24($s0)
L_13: 
lw $t1,-24($s0)
sw $t1,-12($s0)
L_14: 
j L_9
L_15: 
L_16: 
li $t1, 7
li $t2, 13
add $t1, $t1, $t2
sw $t1,-28($s0)
L_17: 
li $v0, 1
lw $t0,-28($s0)
move $a0, $t0
syscall
L_18: 
j L_22
L_19: 
L_20: 
li $v0, 1
li $t0, 4
move $a0, $t0
syscall
L_21: 
j L_22
L_22: 
L_23: 
