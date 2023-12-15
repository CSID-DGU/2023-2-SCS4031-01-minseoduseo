const fromJSONtoDateStr = (date: string, dayName: boolean = false) => {
  const dateObj = new Date(date);
  const year = dateObj.getFullYear();
  const month = String(dateObj.getMonth() + 1).padStart(2, "0");
  const day = String(dateObj.getDate()).padStart(2, "0");
  if (dayName) {
    const dayName = dateObj.toLocaleString("kr", { weekday: "short" });
    return `${year}-${month}-${day} (${dayName})`;
  }
  return `${year}-${month}-${day}`;
};

export { fromJSONtoDateStr };
