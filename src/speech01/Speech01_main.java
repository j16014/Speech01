package speech01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class Speech01_main {
	public static	void main(String[] orgs) {
		TextToSpeech synthesizer = new TextToSpeech();
	    synthesizer.setUsernameAndPassword("j16014",
	    		"j16014");

	    //喋る文章
	    String translation = "今日は良い天気ですね";

	    SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
	    	       .text(translation)
	    	       .voice(SynthesizeOptions.Voice.JA_JP_EMIVOICE)
	    	       .accept(SynthesizeOptions.Accept.AUDIO_WAV)
	    	       .build();
	    	   InputStream in = synthesizer.synthesize(synthesizeOptions).execute();
	    	try {
				writeToFile(WaveUtils.reWriteWaveHeader(in), new File("output.wav"));
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

	}

	//InputStreamはjava.ioをインポート
	private static void writeToFile(InputStream in, File file) {
	    try {
	      //InputStreamと同様
	      OutputStream out = new FileOutputStream(file);
	      byte[] buf = new byte[1024];
	      int len;
	      while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	      }
	      out.close();
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	}
}
