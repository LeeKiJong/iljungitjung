import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";

import CustomButton from "@components/common/CustomButton";
import CustomChip from "@components/common/CustomChip";
import styles from "@styles/Search/SearchResult.module.scss";
import { RootState } from "@modules/index";

const SearchResult = () => {
  const { id, name, imgUrl, desc, detail, categories } = useSelector(
    (state: RootState) => state.search
  );
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/calendar/${id}`);
  };

  return (
    <div className={styles["search-result"]}>
      <div className={styles["profile"]}>
        <div className={styles["img"]}>
          <img src={imgUrl} />
        </div>
        <div className={styles["right"]}>
          <div className={styles["name"]}>{name}</div>
          <div className={styles["desc"]}>{desc}</div>
        </div>
      </div>
      <div className={styles["detail"]}>{detail}</div>
      <div className={styles["categories"]}>
        {categories.map((category, index) => (
          <div className={styles["chip"]} key={index}>
            <CustomChip label={category} onClick={() => {}} />
          </div>
        ))}
      </div>
      <div className={styles["button"]}>
        <CustomButton
          variant="contained"
          children="바로가기"
          onClick={handleClick}
        />
      </div>
    </div>
  );
};

export default SearchResult;