/*
 * JAVE - A Java Audio/Video Encoder (based on FFMPEG)
 *
 * Copyright (C) 2018- Andre Schild
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ws.schild.jave;

import java.io.File;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import ws.schild.jave.info.AudioInfo;
import ws.schild.jave.info.MultimediaInfo;
import ws.schild.jave.info.VideoInfo;

/** @author a.schild */
public class MultimediaObjectTest extends AMediaTest {

  public MultimediaObjectTest() {
    super(null, "MultimediaObject");
  }

  /** Test of getFile method, of class MultimediaObject. */
  @Test
  public void testGetFile() {
    System.out.println("getFile");
    File file = new File(getResourceSourcePath(), "dance1.avi");
    MultimediaObject instance = new MultimediaObject(file);
    File expResult = file;
    File result = instance.getFile();
    assertEquals(expResult, result);
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo01() throws Exception {
    System.out.println("testGetInfo01");
    File file = new File(getResourceSourcePath(), "dance1.avi");
    MultimediaObject instance = new MultimediaObject(file);
    MultimediaInfo result = instance.getInfo();

    assertEquals("avi", result.getFormat());
    assertEquals(1530, result.getDuration());
    assertNull(result.getAudio());
    assertEquals("rawvideo", result.getVideo().getDecoder());
    assertEquals(320, result.getVideo().getSize().getWidth());
    assertEquals(240, result.getVideo().getSize().getHeight());
    assertEquals(4817000, result.getVideo().getBitRate());
    assertEquals(15.0f, result.getVideo().getFrameRate());
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo02() throws Exception {
    System.out.println("testGetInfo02");
    File file = new File(getResourceSourcePath(), "4channels.ogg");
    MultimediaObject instance = new MultimediaObject(file);
    MultimediaInfo result = instance.getInfo();

    assertEquals("ogg", result.getFormat());
    assertEquals(20000, result.getDuration());
    assertNull(result.getVideo());
    assertEquals("vorbis", result.getAudio().getDecoder());
    assertEquals(48000, result.getAudio().getSamplingRate());
    assertEquals(4, result.getAudio().getChannels());
    assertEquals(959000, result.getAudio().getBitRate());
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo03() throws Exception {
    System.out.println("testGetInfo03");
    File file = new File(getResourceSourcePath(), "2019V7HR.amr");
    MultimediaObject instance = new MultimediaObject(file);
    try {
      instance.getInfo();
      assertEquals(1, 1, "Invalid data in header not thrown");
    } catch (Exception ex) {
      assertEquals(1, 1);
    }
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo04() throws Exception {
    System.out.println("testGetInfo04");
    URL source = new URL("https://samples.ffmpeg.org/MPEG1/zelda%20first%20commercial.mpeg");
    MultimediaObject instance = new MultimediaObject(source);
    MultimediaInfo result = instance.getInfo();
    assertEquals(result.getFormat(), "mpeg", "Invalid video format");
    assertEquals(result.getDuration(), 29800, "Invalid duration");
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  //    @Test
  //    public void testGetInfo05() throws Exception {
  //        System.out.println("testGetInfo05");
  //        File file = new File(getResourceSourcePath(), "PCRecorded.mp4");
  //        MultimediaObject instance = new MultimediaObject(file);
  //        MultimediaInfo result = instance.getInfo();
  //        assertEquals("matroska", result.getFormat(),  "Invalid video format");
  //        assertEquals("vp8", result.getVideo().getDecoder(), "Invalid video decoder format");
  //        assertEquals("opus", result.getAudio().getDecoder(), "Invalid audio decoder format");
  //
  //    }
  //
  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo06() throws Exception {
    System.out.println("testGetInfo06");
    File file = new File(getResourceSourcePath(), "size1.mp4");
    MultimediaObject instance = new MultimediaObject(file);
    MultimediaInfo result = instance.getInfo();
    assertEquals("mov", result.getFormat(), "Invalid video format");
    assertEquals(640, result.getVideo().getSize().getHeight(), "Video height not as expected");
    assertEquals(360, result.getVideo().getSize().getWidth(), "Video width not as expected");
  }

  /**
   * Test of getInfo method, of class MultimediaObject.
   *
   * @throws java.lang.Exception
   */
  @Test
  public void testGetInfo07() throws Exception {
    System.out.println("testGetInfo07");
    File file = new File(getResourceSourcePath(), "size2.mp4");
    MultimediaObject instance = new MultimediaObject(file);
    MultimediaInfo result = instance.getInfo();
    assertEquals("mov", result.getFormat(), "Invalid video format");
    assertEquals(960, result.getVideo().getSize().getHeight(), "Video height not as expected");
    assertEquals(544, result.getVideo().getSize().getWidth(), "Video width not as expected");
  }

	/**
	 * Test of getInfo method, of class MultimediaObject.
	 *
	 * @throws java.lang.Exception
	 */
	@Test
	public void testGetInfo08() throws Exception {
		System.out.println("testGetInfo08");
		File file = new File(getResourceSourcePath(), "small.mp4");
		MultimediaObject instance = new MultimediaObject(file);
		MultimediaInfo result = instance.getInfo();
        System.out.println("decoder: " + result.getVideo().getDecoder());
        System.out.println("format: " + result.getFormat());

		assertEquals("mov", result.getFormat());
		assertEquals(5570, result.getDuration());
		assertNotNull(result.getMetadata());
		assertEquals(4, result.getMetadata().size());
		assertEquals("2010-03-20T21:29:11.000000Z", result.getMetadata().get("creation_time"));
		assertEquals("mp42", result.getMetadata().get("major_brand"));
		assertEquals("0", result.getMetadata().get("minor_version"));
		assertEquals("mp42isomavc1", result.getMetadata().get("compatible_brands"));
		assertNotNull(result.getVideo());
		assertEquals("h264 (Constrained Baseline) (avc1 / 0x31637661)", result.getVideo().getDecoder());
		assertEquals(560, result.getVideo().getSize().getWidth());
		assertEquals(320, result.getVideo().getSize().getHeight());
		assertEquals(465000, result.getVideo().getBitRate());
		assertEquals(30f, result.getVideo().getFrameRate());
		assertNotNull(result.getAudio());
		assertEquals("aac (LC) (mp4a / 0x6134706D)", result.getAudio().getDecoder());
		assertEquals(48000, result.getAudio().getSamplingRate());
		assertEquals(1, result.getAudio().getChannels());
		assertEquals(83000, result.getAudio().getBitRate());
	}

    /**
     * Test of getInfo method, of class MultimediaObject.
     * Test reading video and audio metadata
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInfo09() throws Exception {
        System.out.println("testGetInfo09");
        File file = new File("src/test/resources/small.mp4");
        MultimediaObject instance = new MultimediaObject(file);
        MultimediaInfo result = instance.getInfo();

        assertNotNull(result.getVideo());

        VideoInfo videoInfo = result.getVideo();

        assertNotNull(videoInfo.getMetadata());
        assertNotNull(videoInfo.getMetadata().get("creation_time"));

        assertNotNull(result.getAudio());

        AudioInfo audioInfo = result.getAudio();

        assertNotNull(audioInfo.getMetadata());
        assertNotNull(audioInfo.getMetadata().get("creation_time"));
    }

}
