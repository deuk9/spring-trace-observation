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
