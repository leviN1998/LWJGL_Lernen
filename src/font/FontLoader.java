package font;

import font.FontData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FontLoader {
    public FontLoader() {
    }

    public static FontData loadFont(String file) {
        FileReader fr = null;

        try {
            fr = new FileReader(new File("res/" + file + ".fnt"));
        } catch (FileNotFoundException var23) {
            System.err.println("Couldn\'t load file!");
            var23.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fr);
        ArrayList asciiCodes = new ArrayList();
        ArrayList xs = new ArrayList();
        ArrayList ys = new ArrayList();
        ArrayList widths = new ArrayList();
        ArrayList heights = new ArrayList();
        ArrayList xOffsets = new ArrayList();
        ArrayList yOffsets = new ArrayList();
        ArrayList xAdvances = new ArrayList();
        Object asciiCodesArray = null;
        Object xArray = null;
        Object yArray = null;
        Object widthsArray = null;
        Object heightsArray = null;
        Object xOffsetsArray = null;
        Object yOffsetsArray = null;
        Object xAdvancesArray = null;

        try {
            String line;
            do {
                while(true) {
                    line = reader.readLine();
                    String[] i = line.split(" ");
                    if(!line.startsWith("char id")) {
                        break;
                    }

                    for(int i1 = 0; i1 < i.length; ++i1) {
                        List list;
                        if(i[i1].startsWith("id=")) {
                            list = splitString(i[i1], 2);
                            addListToList(list, asciiCodes);
                        }

                        if(i[i1].startsWith("x=")) {
                            list = splitString(i[i1], 1);
                            addListToList(list, xs);
                        }

                        if(i[i1].startsWith("y=")) {
                            list = splitString(i[i1], 1);
                            addListToList(list, ys);
                        }

                        if(i[i1].startsWith("width=")) {
                            list = splitString(i[i1], 5);
                            addListToList(list, widths);
                        }

                        if(i[i1].startsWith("height=")) {
                            list = splitString(i[i1], 6);
                            addListToList(list, heights);
                        }

                        if(i[i1].startsWith("xoffset=")) {
                            list = splitString(i[i1], 7);
                            addListToList(list, xOffsets);
                        }

                        if(i[i1].startsWith("yoffset=")) {
                            list = splitString(i[i1], 7);
                            addListToList(list, yOffsets);
                        }

                        if(i[i1].startsWith("xadvance=")) {
                            list = splitString(i[i1], 8);
                            addListToList(list, xAdvances);
                        }
                    }
                }
            } while(!line.startsWith("kernings"));

            reader.close();
        } catch (Exception var24) {
            var24.printStackTrace();
        }

        int[] var25 = new int[asciiCodes.size()];

        int var33;
        for(var33 = 0; var33 < asciiCodes.size(); ++var33) {
            var25[var33] = ((Integer)asciiCodes.get(var33)).intValue();
        }

        int[] var26 = new int[xs.size()];

        for(var33 = 0; var33 < xs.size(); ++var33) {
            var26[var33] = ((Integer)xs.get(var33)).intValue();
        }

        int[] var27 = new int[ys.size()];

        for(var33 = 0; var33 < ys.size(); ++var33) {
            var27[var33] = ((Integer)ys.get(var33)).intValue();
        }

        int[] var28 = new int[widths.size()];

        for(var33 = 0; var33 < widths.size(); ++var33) {
            var28[var33] = ((Integer)widths.get(var33)).intValue();
        }

        int[] var29 = new int[heights.size()];

        for(var33 = 0; var33 < heights.size(); ++var33) {
            var29[var33] = ((Integer)heights.get(var33)).intValue();
        }

        int[] var30 = new int[xOffsets.size()];

        for(var33 = 0; var33 < xOffsets.size(); ++var33) {
            var30[var33] = ((Integer)xOffsets.get(var33)).intValue();
        }

        int[] var31 = new int[yOffsets.size()];

        for(var33 = 0; var33 < yOffsets.size(); ++var33) {
            var31[var33] = ((Integer)yOffsets.get(var33)).intValue();
        }

        int[] var32 = new int[xAdvances.size()];

        for(var33 = 0; var33 < xAdvances.size(); ++var33) {
            var32[var33] = ((Integer)xAdvances.get(var33)).intValue();
        }

        return new FontData(var25, var26, var27, var28, var29, var30, var31, var32);
    }

    private static List<Integer> splitString(String s, int index) {
        ArrayList ints = new ArrayList();
        String string = "";
        int i = index + 1;

        while(true) {
            if(string.equals("")) {
                string = "" + s.charAt(i++);
            }

            try {
                string = string + s.charAt(i++);
            } catch (Exception var6) {
                ints.add(Integer.valueOf(Integer.parseInt(string)));
                return ints;
            }
        }
    }

    private static List<Integer> addListToList(List<Integer> a, List<Integer> b) {
        for(int i = 0; i < a.size(); ++i) {
            b.add(a.get(i));
        }

        return b;
    }
}

