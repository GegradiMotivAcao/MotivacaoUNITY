import os
import shutil

class Imagens: 
    def __init__(self, path, tipo, nome, pos): 
        self.path = path
        self.tipo = tipo
        self.nome = nome
        self.pos = pos

def CopiaArq(srcPath,dstPath):
	print(srcPath)
	print(dstPath)
	for root, dirs, files in os.walk(srcPath):
		for file in files:
			print(file)
			oldPath = os.path.join(root, file)
			newPath = os.path.join(dstPath, file)
			if '.txt' in file:
				shutil.move(oldPath, newPath)
				print('Movido')


def junta(Lista, path, imagem, tipo_int, pos_int):
    lista.append( Imagens(path, tipo_int, imagem, pos_int ))
    
    
    arquivo= open('demofile.txt', 'w') 
    for obj in lista:
        arquivo.write( obj.path + obj.nome+' ' + str(obj.tipo)+' ' + str(obj.pos) + '\n')
           
pass

lista = []
junta(lista, '/teste/teste/', 'imagem001.jpg', 1, 9)
junta(lista, '/teste/teste2/', 'imagem002.jpg',2, 5)

CopiaArq(r'C:\Users\pedro\Desktop\PY-MOTIVACAO\pasta1', r'C:\Users\pedro\Desktop\PY-MOTIVACAO\pasta2') 
