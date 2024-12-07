# 자바 다운로드

```shell
sudo mkdir /usr/local/java

cd /usr/local/java

sudo wget https://github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.15%2B10/OpenJDK11U-jdk_x64_linux_hotspot_11.0.15_10.tar.gz

sudo tar xvf OpenJDK11U-jdk_x64_linux_hotspot_11.0.15_10.tar.gz

sudo ln -sf /usr/local/java/jdk-11.0.15+10/ /usr/local/java/java11
```

# 자바 환경변수 등록

```shell

sudo vi ~/.bashrc

#하단에 추가
export JAVA_HOME=/usr/local/java/java11
export PATH=$PATH:$JAVA_HOME/bin

# 저장
* esc > : > wq!

# 적용
source ~/.bashrc

```

# java -version
```shell
java -version

```

java 버전이 나온다. 

