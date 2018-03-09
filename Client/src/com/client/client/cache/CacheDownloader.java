package com.client.client.cache;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.client.client.signlink;

public class CacheDownloader implements Runnable {
   /*
	private static final String CACHE_PATH = System.getProperty("user.home") + File.separator + "BloodshedCache" + File.separator;
	private static final String ZIP_URL = "http://amulius.co.uk/BloodshedCache.zip"; // Okkk ty i gtg now 
	private static final String VERSION_FILE = CACHE_PATH + "cacheVersion.dat";
	*/
	
	private static final String CACHE_PATH = System.getProperty("user.home") + File.separator + "BloodshedCache" + File.separator;
	private static final String ZIP_URL = "http://bloodshed-ps.com/client/cache/BloodshedCache.zip";  
	public final String VERSION_URL = "http://bloodshed-ps.com/client/version.txt";
	private static final String VERSION_FILE = CACHE_PATH + "cacheVersion.dat";
	//this is why whats the server name it has to change now. 

	private CacheDownloader.GUI g;

	public double getCurrentVersion(){
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(VERSION_FILE)));
			//System.out.println(Double.parseDouble(br.readLine()) + " current");
			return Double.parseDouble(br.readLine());
		} catch (Exception e) {
			return 0.1;
		}
	}

	public double getNewestVersion(){
		try {
			URL tmp = new URL(VERSION_URL);
			BufferedReader br = new BufferedReader(new InputStreamReader(tmp.openStream()));
			//System.out.println(Double.parseDouble(br.readLine()) + " newest");
			return Double.parseDouble(br.readLine());
		} catch (Exception e) {
			return -1;
		}
	}

	private void handleException(Exception e) {
		StringBuffer strBuff = new StringBuffer();

		strBuff.append("Please Screenshot this message, and send it to an admin!\r\n\r\n");
		strBuff.append(e.getClass().getName() + " \"" + e.getMessage() + "\"\r\n");

		for (StackTraceElement s : e.getStackTrace()) {
			strBuff.append(s.toString() + "\r\n");
		}

		alert("Exception [" + e.getClass().getSimpleName() + "]", strBuff.toString(), true);
	}

	private void alert(String title, String msg, boolean error) {
		JOptionPane.showMessageDialog(null, msg, title, (error ? JOptionPane.ERROR_MESSAGE : JOptionPane.PLAIN_MESSAGE));
	}

	@Override
	public void run() {
		try {
			double newest = getNewestVersion();
			double current = getCurrentVersion();

			if (newest != current) {
				g = new CacheDownloader.GUI();
				g.setLocationRelativeTo(null);
				g.setVisible(true);
				updateCache();
				new FileOutputStream(VERSION_FILE).write(String.valueOf(newest).getBytes());
				g.dispose();
			}
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void updateCache() {
		File clientZip = downloadCache();

		if (clientZip != null) {
			unZip(clientZip);
		}
	}

	private void unZip(File clientZip) {
		try {
			unZipFile(clientZip, new File(signlink.findcachedir()));
			Files.delete(clientZip.toPath());
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void unZipFile(File zipFile, File outFile) throws IOException {
		g.setStatus("Unzipping BloodshedCache...");
		g.setPercent(0);

		ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

		ZipEntry e;

		long max = 0;
		long curr = 0;

		while ((e = zin.getNextEntry()) != null) {
			max += e.getSize();
		}

		zin.close();

		ZipInputStream in = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

		while ((e = in.getNextEntry()) != null) {
			if (e.isDirectory()) {
				new File(outFile, e.getName()).mkdirs();
			} else {
				FileOutputStream out = new FileOutputStream(new File(outFile, e.getName()));

				byte[] b = new byte[1024];

				int len;

				while ((len = in.read(b, 0, b.length)) > -1) {
					curr += len;
					out.write(b, 0, len);
					g.setPercent((int) ((curr * 100) / max));
				}

				out.flush();
				out.close();
			}
		}

		in.close();
	}

	private File downloadCache() {
		g.setStatus("Downloading BloodshedCache...");

		File ret = new File(CACHE_PATH + "BloodshedCache.rar");

		try (OutputStream out = new FileOutputStream(ret)) {
			URLConnection conn = new URL(ZIP_URL).openConnection();
			InputStream in = conn.getInputStream();

			long max = conn.getContentLength();
			long curr = 0;

			byte[] b = new byte[1024];

			int len;

			while ((len = in.read(b, 0, b.length)) > -1) {
				out.write(b, 0, len);
				curr += len;
				g.setPercent((int) ((curr * 100) / max));
			}

			out.flush();
			in.close();
			return ret;
		} catch (Exception e) {
			handleException(e);
			ret.delete();
			return null;
		}
	}

	public class GUI extends JFrame {
		private static final long serialVersionUID = 1L;

		/** Creates new form GUI */
		public GUI() {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception ignored) {

			}

			initComponents();
		}

		/**
		 * This method is called from within the constructor to initialize the
		 * form. WARNING: Do NOT modify this code. The content of this method is
		 * always regenerated by the Form Editor.
		 */
		private void initComponents() {
			jProgressBar1 = new JProgressBar();
			jLabel1 = new JLabel();
			jLabel2 = new JLabel();
			jLabel3 = new JLabel();

			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Bloodshed Cache  Update");

			addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent evt) {
					formWindowClosing(evt);
				}
			});

			jLabel1.setText("Status:");
			jLabel2.setText("N/A");
			jLabel3.setText("0%");

			GroupLayout layout = new GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
					layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.LEADING)
							.addGroup(
									layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
									.addComponent(jLabel3)).addComponent(jProgressBar1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)).addContainerGap()));
			layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
					layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel3))
							.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(jProgressBar1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			pack();
		}

		private void formWindowClosing(java.awt.event.WindowEvent evt) {

		}

		private int percent = 0;

		public void setStatus(String s) {
			jLabel2.setText(s);
		}

		public String getStatus() {
			return jLabel2.getText();
		}

		public void setPercent(int amount) {
			percent = amount;
			jLabel3.setText(amount + "%");
			jProgressBar1.setValue(amount);
		}

		public int getPercent() {
			return percent;
		}

		private JLabel jLabel1;
		private JLabel jLabel2;
		private JLabel jLabel3;
		private JProgressBar jProgressBar1;
	}
}
