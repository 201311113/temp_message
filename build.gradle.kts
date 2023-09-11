import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("io.r2dbc:r2dbc-h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	//FCM
	implementation("com.google.firebase:firebase-admin:9.1.1")

	//redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("io.lettuce:lettuce-core")

	//Swagger
	implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.6")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.6")
	implementation("org.springdoc:springdoc-openapi-webmvc-core:1.6.6")

	//mongo db
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	//json parser
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// log
	implementation("io.github.microutils:kotlin-logging:2.1.23")
	implementation("net.logstash.logback:logstash-logback-encoder:7.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
