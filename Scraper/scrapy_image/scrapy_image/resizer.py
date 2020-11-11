import cv2
import numpy as np

class Resizer():
	def resize(img, perfect_rate): #perfect_rate = height/width ideal das imagem
		image_toresize = cv2.imread(img)     
		height, width = image_toresize.shape[:2]

		if height/width > perfect_rate: #Devemos aumentar x(width) para balancear altura grande
			x = int((height/perfect_rate)-width)
			x_offset=int(x/2-1)
			y = 0
			y_offset=0
			print("1: "+height/width)
		else:							#Devemos aumentar y(height) para balancear largura grande
			y = int((width*perfect_rate)-height)
			y_offset=int(y/2-1)
			x = 0
			x_offset=0
			print(height/width)


		blank_image = np.zeros((height+y,width+x,3), np.uint8)
		blank_image[:,:] = (255)

		l_img = blank_image.copy()                    # (600, 900, 3)

		
		# Here, y_offset+height <= blank_image.shape[0] and x_offset+width <= blank_image.shape[1]
		l_img[y_offset:y_offset+height, x_offset:x_offset+width] = image_toresize.copy()

		cv2.imwrite(img, l_img)
		cv2.waitKey(0)
		cv2.destroyAllWindows()