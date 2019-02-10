# Sticket - Server

## WAR 배포

1. `Edit configurations..` 
2. 왼쪽 상단 `+`
3. `Maven` 선택
4. `command line`에 `clean package -Dmaven.test.skip=true` 입력
5. `Apply` & `OK`
6. 방금 만든 설정을 선택 후 `Run`(`Alt` + `Shift` + `f10`)
7. `war`파일을 찾은 후 ec2의 `/var/lib/tomcat8/webapps/` 디렉터리 밑에 전송
    - `filezilla` 등 ftp 클라이언트 이용
8. 배포 완료. 테스트 ㄱㄱ


