import { useDispatch, useSelector } from "react-redux";
import { WeekView } from "@devexpress/dx-react-scheduler-material-ui";

import styles from "@styles/Calendar/Calendar.module.scss";
import { getDay, getStringFromDate } from "@components/Calendar/common/util";
import { SchedulerDate, SchedulerDateTime } from "@components/types/types";
import { RootState } from "@modules/index";
import { setSelectedTime } from "@modules/othercalendar";

const now = new Date();

const isSameTime = (
  date1: SchedulerDateTime | undefined,
  date2: Date | undefined
) => {
  if (!date1 || !date2) {
    return false;
  }

  const _date1 = new Date(date1.toString());

  return _date1.getTime() === date2.getTime();
};

export default function OtherWeekView() {
  const { selected, minutes, lock, blockList, fixedBlockList } = useSelector(
    (state: RootState) => state.othercalendar
  );
  const dispatch = useDispatch();

  const handleClick = (startDate: SchedulerDateTime) => {
    const newSelected: SchedulerDate = { startDate };

    if (selected?.endDate) {
      const newEndDate = new Date(startDate.toString());
      newEndDate.setMinutes(newEndDate.getMinutes() + minutes);
      newSelected.endDate = newEndDate;
    }

    dispatch(setSelectedTime(newSelected));
  };

  return (
    <WeekView
      startDayHour={9}
      endDayHour={22}
      dayScaleCellComponent={(props) => (
        <WeekView.DayScaleCell
          {...props}
          className="day-scale"
          formatDate={getDay}
        />
      )}
      timeTableCellComponent={(props) => {
        let isDisabled = false;

        if (props.startDate && props.endDate) {
          const startDate = props.startDate;

          if (startDate <= now) {
            isDisabled = true;
          } else {
            const day = (startDate.getDay() + 6) % 7;
            const time =
              startDate.getHours().toString() +
              startDate.getMinutes().toString();

            if (lock[day]) {
              isDisabled = fixedBlockList.get(day)?.includes(time) ?? false;
            } else {
              const date = getStringFromDate(startDate) + time;
              isDisabled = blockList.has(date);
            }
          }
        }

        return (
          <WeekView.TimeTableCell
            {...props}
            className={styles.timeTableCell}
            children={
              <div
                className={
                  isDisabled
                    ? styles["timeTableCell-disabled"]
                    : isSameTime(selected?.startDate, props.startDate) &&
                      !selected?.endDate
                    ? styles["timeTableCell-selected"]
                    : styles["timeTableCell-inner"]
                }
                onClick={(e) => {
                  const className = e.currentTarget.className;

                  if (props.startDate && !className.includes("disabled")) {
                    handleClick(props.startDate);
                  }
                }}
              ></div>
            }
          />
        );
      }}
    />
  );
}
