import sys
import os
import platform
import shutil
def PythonLocation():
	return os.path.dirname(os.path.realpath(__file__)).replace("\\","/")

def main():
	file_path =  os.path.splitext(__file__.replace("\\","/"))[0][os.path.splitext(__file__.replace("\\","/"))[0].rfind("/")+1:]
	if os.path.isfile(PythonLocation()+"/../../z_PythonCode/"+file_path+".py"):
		if os.path.isfile(PythonLocation()+"/"+file_path+".py"):
			os.remove(PythonLocation()+"/"+file_path+".py")
		shutil.copy(PythonLocation()+"/../../z_PythonCode/"+file_path+".py",PythonLocation()+"/"+file_path+".py")

	os.chdir(PythonLocation())
	if os.path.isfile(PythonLocation()+"/app-release.apk"):
		os.remove(PythonLocation()+"/app-release.apk")
	os.system("gradle clean assemblerelease")
	print("------------")
	# print(PythonLocation())
	print(PythonLocation()+"/app/build/outputs/apk/release/app-release.apk")
	os.system("pwd")
	if os.path.isfile(PythonLocation()+"/app-release.apk"):
		os.remove(PythonLocation()+"/app-release.apk")
	if os.path.isfile(PythonLocation()+"/app/build/outputs/apk/release/app-release.apk"):
		os.rename(PythonLocation()+"/app/build/outputs/apk/release/app-release.apk",PythonLocation()+"/app-release.apk")

if __name__ == '__main__':
    main()
"""
@echo off
if exist game-release.apk (DEL game-release.apk)
call .\gradlew.bat clean assemblerelease
MOVE app\build\outputs\apk\release\app-release.apk game-release.apk
"""
