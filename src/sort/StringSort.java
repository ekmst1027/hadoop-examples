package sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class StringSort {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "StringSort");
		job.setJarByClass(StringSort.class);
		
		// mapper ����(�⺻ mapper�� ���, �ԷµǴ� ���ڵ尡 �״�� ��� ���ڵ尡 ��)
		job.setMapperClass(Mapper.class);
		
		// reducer ����(�⺻ reducer�� ���)
		// �ʿ��� ��µǴ� ���� �״�� ���ེ�� ����� ��, ���ེ �ܿ��� ���������� sort�� �̷����
		job.setReducerClass(Reducer.class);
		
		// �� ��°� ���ེ ����� key, value Ÿ�� ����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// reduce�� ���� 1�� ���� (��� ���� ����� �ϳ��� ���ེ �½�ũ�� ���� ��,
		// ���ڵ� ������ �״���̸� sort�� �ϰ� ��)
		job.setNumReduceTasks(1);
		
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		// �Է� ����
		FileInputFormat.addInputPath(job, new Path(args[0]));	
		// ��� ���丮(����� ���̱� ���� ������ ��Ĺ ���, 60% ���� ������ ����)
		SequenceFileOutputFormat.setOutputPath(job, new Path(args[1]));
		// ��� ���� ����(���ڵ� ������ ����)
		SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);
		
		job.waitForCompletion(true);
		
	}
}
