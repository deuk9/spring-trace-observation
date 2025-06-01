## Spring 에서 trace 데이터 수집하기

### 사용한 라이브러리
```kotlin
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-otlp")
    implementation("com.github.loki4j:loki-logback-appender:1.6.0")

    implementation("net.ttddyy.observation:datasource-micrometer-spring-boot:1.1.1")
```

- `micrometer-tracing-bridge-otel` micrometer 와 opentelemetery 를 호환되게함.
- `loki-logback-appender` loki에 바로 append하는 라이브러리
- `opentelemetry-exporter-otlp` trace 정보를 trace 수집기(tempo) 에 전송
- `datasource-micrometer-spring-boot` datasource trace 데이터 수집을 위한 라이브러리


### 사용한 인프라 스택
 - docker
 - tempo: trace 데이터 수집
 - loki: log 수집
 - grafana: 대시보드
 - postgresql


### 블로그 글 참고
[deuk9.dev.blog](https://deuk9.github.io/)
