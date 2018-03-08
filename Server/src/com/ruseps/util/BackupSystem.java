package com.ruseps.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ruseps.GameServer;

/**
 * Created at: Jul 29, 2016 4:54:14 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class BackupSystem implements Runnable {

	private static File BACKUP_SAVE = new File("data/saves_backup/");
	private static File BACKUP_TARGET = new File("data/saves/");
	private static SimpleDateFormat formatter = new SimpleDateFormat("H dd MMMM yyyy");

	public void initialize() {
		if (!BACKUP_SAVE.exists()) {
			BACKUP_SAVE.mkdirs();
		}
		GameServer.getLoader().getEngine().getExecutor().scheduleAtFixedRate(this, 0, 6, TimeUnit.HOURS);
	}

	public static void main(String[] args) {
		BackupSystem system = new BackupSystem();
		system.initialize();
		system.run();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		zip_directory(BACKUP_TARGET, new File(BACKUP_SAVE, formatter.format(System.currentTimeMillis()) + ".zip"));
	}

	private void zip_directory(File input_file, File output_file) {
		try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(output_file))) {
			add_zip_directory(input_file, input_file, zip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add_zip_directory(File main_file, File input_file, ZipOutputStream output_file) {
		for (File src_file : input_file.listFiles()) {
			if (src_file.isDirectory()) {
				add_zip_directory(main_file, src_file, output_file);
			} else {
				add_zip_file(main_file, src_file, output_file);
			}
		}
	}

	private void add_zip_file(File main_file, File input_file, ZipOutputStream output_file) {
		try (FileInputStream in = new FileInputStream(input_file)) {
			byte[] buffer = new byte[1024];
			output_file.putNextEntry(new ZipEntry(input_file.getPath().substring(main_file.getPath().length() + 1, input_file.getPath().length())));
			int read;
			while ((read = in.read(buffer)) > 0) {
				output_file.write(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
