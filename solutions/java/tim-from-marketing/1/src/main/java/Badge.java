class Badge {
    public String print(Integer id, String name, String department) {
        String idPart = id != null ? "[" + id + "] - " : "";
        String namePart = name != null ? name + " - " : "";
        String departmentPart = department != null ? department.toUpperCase() : "OWNER";

        return idPart + namePart + departmentPart;
    }
}
