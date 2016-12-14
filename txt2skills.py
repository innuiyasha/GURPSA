print("test")

x = 0

while (x < 5):
	print x
	x += 1
print("done")


with open("test.txt") as f:
	content = f.readlines()
	print(content)
