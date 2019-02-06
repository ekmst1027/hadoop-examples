package count;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Mapper<�Է�key�ڷ���, �Է�value�ڷ���, ���key�ڷ���, ���value�ڷ���>
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	// ���� 1�� ���� ����� �� ����
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	// ���ι�ȣ, �ؽ�Ʈ ������ ������ �о �ܾ�, ī��Ʈ �������� ��ȯ
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString());
		while(st.hasMoreTokens()) {
			word.set(st.nextToken());
			context.write(word,  one);
		}
	}
}
