package com.example.springbatch.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    /**
     * tcp: TCP 서버로 H@가 실행되도록 설정한다
     * tcpAllowOthers: 다른 외부에서 접속 가능하게 하는 설정
     * tcpPort: 포트 번호를 지정
     * @return
     * @throws SQLException
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server H2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
    }

}
