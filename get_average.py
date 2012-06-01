import sys

file = sys.argv[1]

fd = open (file, 'r')

summ = 0.0
count = 0.0
for line in fd:
	summ = summ + int(line)	
	count = count + 1

print summ/count
