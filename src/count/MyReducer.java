package count;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

// Reducer<�Է�key�ڷ���, �Է�value�ڷ���, ���key�ڷ���, ���value�ڷ���>
public class MyReducer 
	extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();
	// key, ����Ʈ�� ��ȯ�ϴ� �ڵ�
	public void reduce(Text key, Iterable<IntWritable> values, 
			Context context) throws IOException, InterruptedException {
		int sum = 0;
		for(IntWritable val : values) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}
