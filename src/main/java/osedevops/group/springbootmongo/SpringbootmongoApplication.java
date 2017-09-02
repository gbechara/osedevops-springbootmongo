package osedevops.group.springbootmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ProbabilisticSampler;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import brave.sampler.Sampler;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Encoding;
import zipkin.reporter.okhttp3.OkHttpSender;

@SpringBootApplication
@EnableMongoRepositories("osedevops.group.springbootmongo.repository")
@EnableCircuitBreaker
public class SpringbootmongoApplication {

    @Bean
	public io.opentracing.Tracer jaegerTracer() {
		return new Configuration("spring-boot", new Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
				new Configuration.ReporterConfiguration(false, "jaeger-collector.jaeger-infra.svc", 14267, 1000, 10000))
				.getTracer();
	}

	//@Bean
	public io.opentracing.Tracer zipkinTracer() {
		OkHttpSender okHttpSender = OkHttpSender.builder()
				.encoding(Encoding.JSON)
				.endpoint("http://localhost:9411/api/v1/spans")
				.build();
		AsyncReporter<Span> reporter = AsyncReporter.builder(okHttpSender).build();
		Tracing braveTracer = Tracing.newBuilder()
				.localServiceName("spring-boot")
				.reporter(reporter)
				.traceId128Bit(true)
				.sampler(Sampler.ALWAYS_SAMPLE)
				.build();
		return BraveTracer.create(braveTracer);
	}

    
	public static void main(String[] args) {
		SpringApplication.run(SpringbootmongoApplication.class, args);
	}
}
