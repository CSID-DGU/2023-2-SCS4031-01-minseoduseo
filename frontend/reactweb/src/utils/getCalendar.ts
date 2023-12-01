const getFirstDay = (year: number, month: number) => {
  return new Date(year, month - 1, 1).getDay();
};

const getDayCount = (year: number, month: number) => {
  return new Date(year, month + 1, 0).getDate();
};

const getTotalWeeks = (year: number, month: number) => {
  return Math.ceil((getFirstDay(year, month) + getDayCount(year, month)) / 7);
};

const getDateNums = (year: number, month: number) => {
  const firstDay = getFirstDay(year, month);
  const dayCount = getDayCount(year, month);
  const dateNums = Array.from({ length: 42 }, (_, i) => {
    if (i < firstDay || i >= firstDay + dayCount) {
      return null;
    }
    return i - firstDay + 1;
  });
  return dateNums;
};

const getCalendar = (year: number, month: number) => {
  const totalWeeks = getTotalWeeks(year, month);
  const dateNums = getDateNums(year, month);
  const calendar = Array.from({ length: totalWeeks }, (_, i) => {
    return dateNums.slice(i * 7, i * 7 + 7);
  });
  return calendar;
};

export default getCalendar;
