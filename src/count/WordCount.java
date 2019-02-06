package count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount {
	public static void main(String[] args) throws Exception {
		// 하둡 환경 설정 파일 클래스
		Configuration conf = new Configuration();
		// 입력 매개변수가 2개가 아니면 프로그램 종료
		if (args.length != 2) {
			// 에러 메시지 출력
			System.err.println("Usage: WordCount <input> <output>");
			// 프로그램 종료
			System.exit(1);
		}
		// HDFS(하둡 분산 파일 시스템)에 새로운 작업 할당
		Job job = Job.getInstance(conf, "WordCount");
		// jar 파일 내용 중 실행할 클래스 이름 지정
		job.setJarByClass(WordCount.class);
		// mapper 클래스 지정
		job.setMapperClass(MyMapper.class);
		// reducer 클래스 지정
		job.setReducerClass(MyReducer.class);
		// 입력 자료형 지정
		job.setInputFormatClass(TextInputFormat.class);
		// 출력 자료형 지정
		job.setOutputFormatClass(TextOutputFormat.class);
		// key의 자료형 지정
		job.setOutputKeyClass(Text.class);
		// value의 자료형 지정
		job.setOutputValueClass(IntWritable.class);
		// 입력 파일 경로 지정
		FileInputFormat.addInputPath(job, new Path(args[0]));
		// 출력 파일 경로 지정
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// 분석 작업이 끝날 때까지 대기
		job.waitForCompletion(true);
	}
}
