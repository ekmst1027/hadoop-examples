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
		// �ϵ� ȯ�� ���� ���� Ŭ����
		Configuration conf = new Configuration();
		// �Է� �Ű������� 2���� �ƴϸ� ���α׷� ����
		if (args.length != 2) {
			// ���� �޽��� ���
			System.err.println("Usage: WordCount <input> <output>");
			// ���α׷� ����
			System.exit(1);
		}
		// HDFS(�ϵ� �л� ���� �ý���)�� ���ο� �۾� �Ҵ�
		Job job = Job.getInstance(conf, "WordCount");
		// jar ���� ���� �� ������ Ŭ���� �̸� ����
		job.setJarByClass(WordCount.class);
		// mapper Ŭ���� ����
		job.setMapperClass(MyMapper.class);
		// reducer Ŭ���� ����
		job.setReducerClass(MyReducer.class);
		// �Է� �ڷ��� ����
		job.setInputFormatClass(TextInputFormat.class);
		// ��� �ڷ��� ����
		job.setOutputFormatClass(TextOutputFormat.class);
		// key�� �ڷ��� ����
		job.setOutputKeyClass(Text.class);
		// value�� �ڷ��� ����
		job.setOutputValueClass(IntWritable.class);
		// �Է� ���� ��� ����
		FileInputFormat.addInputPath(job, new Path(args[0]));
		// ��� ���� ��� ����
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// �м� �۾��� ���� ������ ���
		job.waitForCompletion(true);
	}
}
