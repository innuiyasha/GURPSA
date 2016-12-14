#!/usr/bin/python



import sys
import re

print("Starting .txt Extraction")




page = [[]]
pIndex = 0

c0 = ""
c1 = ""
c2 = ""


master = ""
fullPages = []


count = 0


with open(sys.argv[1]) as f:
	book = f.readlines()
	

	for linenum in range(len(book)):
		if len(book[linenum]) == 2:		# Pages are seperated by 3 newlines
			if count == 2:
				pIndex += 1
				page.append([])	
				count = 0
			else:
				count += 1
		else:
			page[pIndex].append(book[linenum])

for content in page:
	for linenum in range(len(content)):
		content[linenum] = re.sub("\r\n", "", content[linenum])
		if linenum == 0:
			c0 += content[linenum]
		else:
			offset = 0
			for letternum in range(len(content[linenum])):
				if letternum == 0 and content[linenum][letternum] == " ":
					offset = 1
				elif letternum < 45 + offset:
					c0 += content[linenum][letternum]
				elif letternum < 90 + offset:
					c1 += content[linenum][letternum]
				elif letternum >= 90 + offset:
					c2 += content[linenum][letternum]
	fullPages.append(c0 + c1 + c2)

	i = len(fullPages) - 1
	
	fullPages[i] = re.sub( "(?<=[a-z])\-[\s]+", "", fullPages[i] )

	fullPages[i] = re.sub( '\s+', ' ', fullPages[i] ).strip()
	
	master += fullPages[i]
	c0 = c1 = c2 = ""
		


print(fullPages[int(sys.argv[2])])



# Fix issues such as "Traits you must pos- sess before"
master = re.sub( "(?<=[a-z])\-[\s]+", "", master )


# Remove superfluous whitespace
master = re.sub( '\s+', ' ', master ).strip()


#print(c0)

print("done")
