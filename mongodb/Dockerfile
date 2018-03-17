FROM mongo:3.4
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

# todo remove passwords
ENV AUTH yes
ENV STORAGE_ENGINE wiredTiger
ENV JOURNALING yes
ENV MONGODB_ADMIN_USER=admin
ENV MONGODB_ADMIN_PASS=adminpass
ENV MONGODB_APPLICATION_DATABASE=quizee
ENV MONGODB_APPLICATION_USER=user
ENV MONGODB_APPLICATION_PASS=pass

ADD init.sh /init.sh
ADD change_password.sh /change_password.sh
ADD ./dump /

RUN \
 chmod +x /init.sh && \
 chmod +x /change_password.sh && \
 apt-get update && apt-get dist-upgrade -y && \
 apt-get install psmisc -y -q && \
 apt-get autoremove -y && apt-get clean && \
 rm -rf /var/cache/* && rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["/init.sh"]

EXPOSE 27017