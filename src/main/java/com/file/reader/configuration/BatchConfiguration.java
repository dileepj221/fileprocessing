package com.file.reader.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.file.reader.entity.Employee;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Employee> reader() {
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employItemReader")
                .linesToSkip(1)
                .resource(new ClassPathResource("file.txt"))
                .delimited()
                .names(new String[]{ "name", "branch", "date", "country", "salaryamt", "bonusid", "bonusamt" })
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                    setTargetType(Employee.class);
                }})
                .build();
    }
    
    @Bean
	public LineMapper<Employee> lineMapper() {
		final DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter("~");
		lineTokenizer.setStrict(false);
		lineTokenizer
				.setNames(new String[] { "name", "branch", "date", "country", "salaryamt", "bonusid", "bonusamt" });

		final BalanceFieldSetMapper fieldSetMapper = new BalanceFieldSetMapper();
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

    @Bean
    public BalanceProcessor processor() {
        return new BalanceProcessor();
    }
    
    @Bean
    public Job importVoltageJob(NotificationListener listener, Step step) {
        return jobBuilderFactory.get("importEmployeeJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<Employee> writer) {
        return stepBuilderFactory.get("step")
                .<Employee, Employee> chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .stream(stream())
                .build();
    }
    
    @Bean
    public JdbcBatchItemWriter<Employee> writer(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO employee (name, branch, date, country, salaryamt, bonusid, bonusamt) VALUES (:name, :branch, :date, :country, :salaryamt, :bonusid, :bonusamt)")
                .dataSource(dataSource)
                .build();
    }
    
    @Bean
    public ItemCountItemStream stream() {
        return new ItemCountItemStream();
    }
}
