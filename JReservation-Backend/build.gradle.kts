plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.spring") version "2.2.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Rest etc
    implementation("org.springframework.boot:spring-boot-starter-web")

    //ORM
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //Not blank etc
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //DB
    runtimeOnly("org.postgresql:postgresql")

    //Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //LOMBOK
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.test {
    useJUnitPlatform()
}