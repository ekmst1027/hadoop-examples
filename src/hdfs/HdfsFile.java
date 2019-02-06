package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsFile {
	// args ����� �Ű�����
	public static void main(String[] args) {
		// �Է� �Ķ���� Ȯ��
		if(args.length != 2) {
			// ���� �޽��� ���
			System.err.println(
					"��� ���: HdfsFile <filename> <contents>");
			// ���α׷� ���� ����
			System.exit(2);
		}
		
		try {
			// ���� �ý��� ���� ��ü ����
			Configuration conf = new Configuration();
			// �ϵӺл����Ͻý��� ��ü
			FileSystem hdfs = FileSystem.get(conf);
			
			// ��� üũ
			Path path = new Path(args[0]);
			// ��ΰ� �����ϸ�
			if(hdfs.exists(path)) {
				// ���� ����
				hdfs.delete(path, true);
			}
			
			// ���� ����
			FSDataOutputStream os = hdfs.create(path);
			os.writeUTF(args[1]);
			os.close();
			
			// ���� ���� �б�
			FSDataInputStream is = hdfs.open(path);
			String inputString = is.readUTF();
			is.close();
			// ȭ�鿡 ���
			System.out.println("Input Data:" + inputString);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
