package airline;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// Mapper<Longwritable, Text, Text, IntWritable>
// 줄번호, 문장
public class DepartureDelayCountMapper 
	extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	// map 출력값
	private final static IntWritable outputValue = new IntWritable(1);
	
	// map 출력키
	private Text outputKey = new Text();
	
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException {
		
		AirlinePerformanceParser parser =
				new AirlinePerformanceParser(value);
		
		// 출력키 설정
		outputKey.set(parser.getYear() + "," + parser.getMonth());
		
		if(parser.getDepartureDelayTime() > 0) {
			context.write(outputKey, outputValue);
		}
	}

}
