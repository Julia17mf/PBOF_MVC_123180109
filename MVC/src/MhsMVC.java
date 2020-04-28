public class MhsMVC {
    MhsView mhsView             = new MhsView();
    MhsModel mhsModel           = new MhsModel();
    MhsDAO mhsDAO               = new MhsDAO();
    MhsController mhsController = new MhsController(mhsModel, mhsView, mhsDAO);
}
