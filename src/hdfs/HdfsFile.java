package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsFile {
	// args 명령행 매개변수
	public static void main(String[] args) {
		// 입력 파라미터 확인
		if(args.length != 2) {
			// 에러 메시지 출력
			System.err.println(
					"사용 방법: HdfsFile <filename> <contents>");
			// 프로그램 강제 종료
			System.exit(2);
		}
		
		try {
			// 파일 시스템 제어 객체 생성
			Configuration conf = new Configuration();
			// 하둡분산파일시스템 객체
			FileSystem hdfs = FileSystem.get(conf);
			
			// 경로 체크
			Path path = new Path(args[0]);
			// 경로가 존재하면
			if(hdfs.exists(path)) {
				// 파일 삭제
				hdfs.delete(path, true);
			}
			
			// 파일 저장
			FSDataOutputStream os = hdfs.create(path);
			os.writeUTF(args[1]);
			os.close();
			
			// 파일 내용 읽기
			FSDataInputStream is = hdfs.open(path);
			String inputString = is.readUTF();
			is.close();
			// 화면에 출력
			System.out.println("Input Data:" + inputString);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
