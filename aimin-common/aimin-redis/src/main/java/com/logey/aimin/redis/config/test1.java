//package com.logey.aimin.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@AutoConfiguration
//public class RedisConfiguration {
//
//    @Value("${spring.redis.host:localhost}")
//    private String redisHost;
//
//    @Value("${spring.redis.port:6379}")
//    private int redisPort;
//
//    @Value("${spring.redis.database:0}")
//    private int redisDatabase;
//
//    @Value("${spring.redis.password:}")
//    private String redisPassword;
//
//    @Bean
//    @ConditionalOnMissingBean(RedisConnectionFactory.class)
//    public RedisConnectionFactory redisConnectionFactory() {
//        System.out.println("————————————————创建 RedisConnectionFactory————————————————");
//        System.out.println("使用配置：");
//        System.out.println("  Host: " + redisHost);
//        System.out.println("  Port: " + redisPort);
//        System.out.println("  Database: " + redisDatabase);
//        System.out.println("  Password: " + (redisPassword != null && !redisPassword.isEmpty() ? "******" : "未设置"));
//
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(redisHost);
//        config.setPort(redisPort);
//        config.setDatabase(redisDatabase);
//        if (redisPassword != null && !redisPassword.isEmpty()) {
//            config.setPassword(redisPassword);
//        }
//
//        LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
//        factory.afterPropertiesSet();
//
//        System.out.println("RedisConnectionFactory 创建完成");
//        System.out.println("————————————————————————————————————————");
//        return factory;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(name = "redisTemplate")
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        System.out.println("————————————————配置生效————————————————");
//        System.out.println("Redis配置信息（从配置文件读取）：");
//        System.out.println("  Host: " + redisHost);
//        System.out.println("  Port: " + redisPort);
//        System.out.println("  Database: " + redisDatabase);
//        System.out.println("  Password: " + (redisPassword != null && !redisPassword.isEmpty() && !"未配置".equals(redisPassword) ? "******" : "未配置"));
//
//        // 打印 RedisConnectionFactory 的实际连接信息
//        System.out.println("\nRedisConnectionFactory 实际连接信息：");
//        System.out.println("  Factory类型: " + redisConnectionFactory.getClass().getName());
//
//        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
//            LettuceConnectionFactory lettuceFactory = (LettuceConnectionFactory) redisConnectionFactory;
//            System.out.println("  实际Host: " + lettuceFactory.getHostName());
//            System.out.println("  实际Port: " + lettuceFactory.getPort());
//            System.out.println("  实际Database: " + lettuceFactory.getDatabase());
//            System.out.println("  连接超时: " + lettuceFactory.getTimeout());
//        }
//        System.out.println("————————————————————————————————————————");
//
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        // Key 序列化
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        template.setKeySerializer(stringRedisSerializer);
//        template.setHashKeySerializer(stringRedisSerializer);
//
//        // Value 序列化（使用自定义的 Jackson 序列化器）
//        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = myJasonRedisSerializer();
//        template.setValueSerializer(jsonRedisSerializer);
//        template.setHashValueSerializer(jsonRedisSerializer);
//
//        // 开启事务（可选）
//        template.setEnableTransactionSupport(true);
//        template.afterPropertiesSet();
//
//        return template;
//    }
//
//    private Jackson2JsonRedisSerializer<Object> myJasonRedisSerializer() {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // 设置可见性
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 序列化后添加类型信息
//        objectMapper.activateDefaultTyping(
//                LaissezFaireSubTypeValidator.instance,
//                ObjectMapper.DefaultTyping.NON_FINAL
//        );
//        // 禁用时间戳，使用字符串格式
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        // 自定义时间模块
//        JavaTimeModule timeModule = new JavaTimeModule();
//        // 自定义 LocalDateTime 序列化/反序列化格式
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
//        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
//
//        // 自定义 LocalDate 序列化/反序列化格式
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
//        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
//
//        // 注册时间模块
//        objectMapper.registerModule(timeModule);
//
//        // 构造函数传入 ObjectMapper，适配 Spring Data Redis 3.x+
//        return new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);
//    }
//}