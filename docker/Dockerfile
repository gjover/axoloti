FROM ubuntu:14.04

# Create sudoer user    
RUN useradd -ms /bin/bash user && \
    echo "user:user" | chpasswd && \
    adduser user sudo && \
    echo "user ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers
USER user

COPY ./docker/build.patch /home/user/

# Build axoloti 
RUN cd && \
    sudo apt-get update && \
    sudo apt-get install -qqy git \
        build-essential libtool libudev-dev  \
        automake autoconf ant curl p7zip-full unzip udev \
        gcc-arm-linux-gnueabihf openjdk-7-jdk && \
    git clone http://github.com/axoloti/axoloti && \
    cd axoloti && \
    git checkout 1.0.12-2 && \
    git apply ~/build.patch && \
    git diff -U0 -C0 && \
    cd platform_linux && \
    ./build.sh && \
# Clean up
    cd /home/user/axoloti && \
    rm -rf platform_* .git && \
    sudo rm -rf /tmp/* /usr/share/doc/* /usr/share/info/* /var/tmp/* \
        /home/user/axoloti/axoloti-contrib/* && \
    sudo apt-get remove -qqy git build-essential libtool libudev-dev \
        automake autoconf ant curl p7zip-full unzip udev \
    	gcc-arm-linux-gnueabihf && \
    sudo apt-get autoremove -qqy && \
    sudo rm -rf /var/lib/apt/lists/*


ENV DISPLAY :0
CMD /home/user/axoloti/Axoloti.sh