import { SchedulerDate } from '@components/types/types';
import { getStringFromDate } from '@components/Calendar/common/util';

/* action type */
const SET_DISABLED_MAP = 'othercalendar/SET_DISABLED_MAP' as const;
const SET_CURRENT = 'othercalendar/SET_CURRENT' as const;
const SET_SELECTED_TIME = 'othercalendar/SET_SELECTED_TIME' as const;
const SET_MINUTES = 'othercalendar/SET_MINUTES' as const;

/* action creator */
export const setDisabledMap = (list: SchedulerDate[]) => ({
  type: SET_DISABLED_MAP,
  payload: list,
});

export const setCurrent = () => ({
  type: SET_CURRENT,
});

export const setSelectedTime = (selected: SchedulerDate) => ({
  type: SET_SELECTED_TIME,
  payload: selected,
});

export const setMinutes = (minutes: number) => ({
  type: SET_MINUTES,
  payload: minutes,
});

type OtherCalenderActions =
  | ReturnType<typeof setDisabledMap>
  | ReturnType<typeof setCurrent>
  | ReturnType<typeof setSelectedTime>
  | ReturnType<typeof setMinutes>;

export interface OtherCalenderState {
  current: SchedulerDate[];
  selected?: SchedulerDate;
  minutes: number;
  map: Map<string, SchedulerDate[]>;
}

const initialState: OtherCalenderState = {
  current: [],
  minutes: 0,
  map: new Map<string, SchedulerDate[]>(),
};

export default function reducer(
  state: OtherCalenderState = initialState,
  action: OtherCalenderActions
) {
  switch (action.type) {
    case SET_DISABLED_MAP:
      for (let item of action.payload) {
        const str = getStringFromDate(item.startDate.toString());

        if (!state.map.has(str)) {
          const list: SchedulerDate[] = [];
          state.map.set(str, list);
        }

        state.map.get(str)?.push(item);
      }

      return state;
    case SET_CURRENT:
      if (state.selected) return { ...state, current: [state.selected] };
      else return state;
    case SET_SELECTED_TIME:
      return { ...state, selected: action.payload };
    case SET_MINUTES:
      return { ...state, minutes: action.payload };
    default:
      return state;
  }
}