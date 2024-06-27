build-and-run: build run

build:
	gradle build

run:
	adb -e install app/build/outputs/apk/debug/app-debug.apk && \
	adb shell monkey -p dev.pierrelegall.tempai -c android.intent.category.LAUNCHER 1 && \
	adb logcat | rg "tempai"

boot:
	~/Android/Sdk/emulator/emulator -avd Pixel_3a_API_31
