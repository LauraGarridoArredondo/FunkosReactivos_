plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone") // Agregar el repositorio de Spring Milestones
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Lombok
    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    // Logger
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // Project Reactor
    implementation("io.projectreactor:reactor-core:3.5.10")
    // H2
    implementation("com.h2database:h2:2.2.224")
    implementation("io.r2dbc:r2dbc-h2:1.0.0.RELEASE") // Dependencia de H2 para R2DBC
    // Ibatis
    implementation("org.mybatis:mybatis:3.5.13")
    // GSON
    implementation("com.google.code.gson:gson:2.10.1")
    // Mockito
    testImplementation("org.mockito:mockito-core:5.6.0")
    //Hikari
    implementation("com.zaxxer:HikariCP:5.0.1")

}

tasks.test {
    useJUnitPlatform()
}
