package test;

import excel.ExcelManager;

public class Test {

    //	public static void main(String[] args) {
//
//		MenuManager manager = new MenuManager();
//		manager.initMenu();
//	}
    public static void main(String[] args) {
        ExcelManager manager = new ExcelManager();
        manager.readExcel();
    }

}

