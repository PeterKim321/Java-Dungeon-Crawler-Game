# Project

NOTE: - This was built for an Assignment. The game was extended to include more features out of interest.
- Make sure that javafx package on eclipse is installed if the program won't load.

## Project setup

**NOTE**: For the first milestone, it is not necessary to set up the project in Eclipse.

Because this project uses JavaFX, it requires some additional setup steps in Eclipse. It's relatively straightforward if you're using a CSE computer, but if you're using your own computer you will need to download an alternate JDK.

0. Go [here](https://www.azul.com/downloads/zulu-community/?&package=jdk-fx) and download the Java 11 Zulu JDK FX for your OS. Unzip it once it finishes downloading.
1. In Eclipse, import this project as normal. You should see an exclamation mark on the project indicating an error.
2. Go to **Window -> Preferences**. On the left, under **Java** select **Installed JREs** then click **Add**. Ensure **Standard VM** is selected and click **Next**.
3. Click **Directory** and select the directory of the Zulu JDK you unzipped in step 1. Change **JRE name** to exactly `jdk-jfx`.
4. Click **Finish** then **Apply and Close**

If these steps worked, the project should no longer have the exclamation mark on it and you should be able to run the program.

*Project setup is from assignment specs.
