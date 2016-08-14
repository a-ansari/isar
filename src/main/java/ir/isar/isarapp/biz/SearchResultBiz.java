package ir.isar.isarapp.biz;

import ir.isar.isarapp.filter.result.FacilityResultColumn;
import ir.isar.isarapp.filter.result.ResultColumn;
import ir.isar.isarapp.filter.result.TermSpecificResultColumn;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Singleton;

/**
 *
 * @author User
 */
@Singleton
public class SearchResultBiz {

    private final ResourceBundle messages = ResourceBundle.getBundle(getClass().getName());

    private List<ResultColumn> allColumns;

    public List<ResultColumn> getAllColumns() {
        if (allColumns == null) {
            allColumns = new ArrayList<>();
            String prefix = "isar.SearchResultBiz.item";
            int itemCount = Integer.parseInt(messages.getString(prefix + "Count"));

            for (int i = 0; i < itemCount; i++) {
                String title = messages.getString(prefix + i + ".title");
                String field = messages.getString(prefix + i + ".field");
                ResultColumn column = new ResultColumn(title, field);
                allColumns.add(column);
            }
        }
        return allColumns;
    }

    private List<Integer> defaultColumns;

    public List<Integer> getDefaultColumns() {
        if (defaultColumns == null) {
            defaultColumns = new ArrayList<>();
            String listStr = messages.getString("isar.SearchResultBiz.default");
            for (String str : listStr.split(",")) {
                defaultColumns.add(Integer.parseInt(str));
            }
        }
        return defaultColumns;
    }

    private List<ResultColumn> bonyadColumns;

    public List<ResultColumn> getBonyadColumns() {
        if (bonyadColumns == null) {
            bonyadColumns = new ArrayList<>();
            String listStr = messages.getString("isar.SearchResultBiz.bonyad");
            for (String str : listStr.split(",")) {
                bonyadColumns.add(getAllColumns().get(Integer.parseInt(str)));
            }
            String komakHazineMsg = messages.getString("isar.SearchResultBiz.komakHazineMsg");
            FacilityResultColumn komakHazine = new FacilityResultColumn(komakHazineMsg,"komakHazine");
            bonyadColumns.add(komakHazine);
        }
        return bonyadColumns;
    }

    public List<TermSpecificResultColumn> getAllTermSpecificColumns() {
        List<TermSpecificResultColumn> result = new ArrayList<>();
        String prefix = "isar.SearchResultBiz.termSpecific.item";
        int itemCount = Integer.parseInt(messages.getString(prefix + "Count"));

        for (int i = 0; i < itemCount; i++) {
            String title = messages.getString(prefix + i + ".title");
            String field = messages.getString(prefix + i + ".field");
            TermSpecificResultColumn column = new TermSpecificResultColumn(title, field);
            result.add(column);
        }
        return result;
    }
}
