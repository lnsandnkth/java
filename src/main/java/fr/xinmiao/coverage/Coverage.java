package fr.mxyns.coverage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Coverage {

    public static AtomicInteger coverageId = new AtomicInteger(0);
    public static List<TreeMap<Integer, Integer>> coverages = new ArrayList<TreeMap<Integer, Integer>>();
    public static final int BRANCH_COUNT_KEY = -2;
    public static final int FUNCTION_CALL_KEY = -1;

    public static int initCoverage(int branchCount) {
        TreeMap<Integer, Integer> coverageInstance = new TreeMap<Integer, Integer>();
        coverageInstance.put(BRANCH_COUNT_KEY, branchCount);
        coverageInstance.put(FUNCTION_CALL_KEY, 0);
        for (int i = 0;  i < branchCount; i++) coverageInstance.put(i, 0);

        coverages.add(coverageInstance);

        int id;
        if ((id = coverageId.getAndIncrement()) + 1 == coverages.size())
            return id;
        else
            return -1;

    }
    public static void dumpResults(int id) {

        TreeMap<Integer, Integer> coverage = coverages.get(id);

        int branchCount = coverage.get(BRANCH_COUNT_KEY);
        int coveredBranches = 0;
        for (Integer branchId : coverage.keySet()) {
            coveredBranches += ((branchId > 0 && coverage.get(branchId) > 0) ? 1 : 0);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(coverage);

        builder.append("=== COVERAGE #" + id + " RESULTS ===\n");
        builder.append("BRANCHES COVERED : ")
               .append(coveredBranches)
               .append("/")
               .append(branchCount)
               .append(" = ")
               .append(100 * coveredBranches / branchCount)
               .append("%\n");
        builder.append("    == PER BRANCH DETAILS ==    ");

        Object[] sortedIds = new ArrayList<Integer>(coverage.keySet()).toArray();
        Arrays.sort(sortedIds);
        for (Object branchId : sortedIds) {
            if (!branchId.equals(-1))
                builder.append("        [" + branchId + "] " + (coverage.get(branchId) > 0 ? coverage.get(branchId) : "NO") + " passes\n");
        }

        try {
            new File("coverage/miao/").mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter("coverage/miao/" + id + ".dmp", false));
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(builder);
    }
    public static void markBranch(int id, Integer branchId) {
        coverages.get(id).put(branchId, coverages.get(id).get(branchId) + 1);
        dumpResults(id);
    }
}
