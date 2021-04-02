import com.google.common.collect.Maps;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author fxzou
 * @date 2021/2/5 0005
 * @since springbootdemo
 */
public class ReadRuleServiceImpl implements IReadRuleService {

    private final static String EMPTY_STRING = "";

    /**
     * 读取规则
     *
     * @param path
     * @return
     */
    @Override
    public Map<String, Object> readRule(String path) {

        //List<RuleAccountBean> resultList = readContent(path);

        Map<String, Object> resultMap = Maps.newHashMap();

        List<File> fileList = Lists.newArrayList();

        File dir = new File(path);

        File[] files = dir.listFiles();

        if (files == null) {
            return resultMap;
        }

        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            }
        }

        if (ListUtils.isEmpty(fileList)) {
            return resultMap;
        }


        List<String[]> dataList = fileList.parallelStream().collect(ArrayList::new, (list, dataFile) -> {

            try (
                    InputStream inputStream = new FileInputStream(dataFile);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    String[] dataValues = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, "\t");

                    List<String> processedList = Arrays.stream(dataValues).map(dataValue -> {
                        if (StringUtils.isEmpty(dataValue)) {
                            return "";
                        } else {
                            return dataValue.replaceAll("\r", EMPTY_STRING).replaceAll("\n", EMPTY_STRING).replaceAll("'", EMPTY_STRING);
                        }
                    }).collect(Collectors.toList());

                    String[] processedValues = new String[dataValues.length];

                    processedValues = processedList.toArray(processedValues);

                    list.add(processedValues);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }, (list1, list2) -> list1.addAll(list2));

        if (CollectionUtils.isEmpty(dataList)) {
            return resultMap;
        }

        return resultMap;
    }


        return resultList;
    }


}
