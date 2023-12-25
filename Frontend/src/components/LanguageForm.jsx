import { useRef } from "react";

export default function LanguageForm({languages ,setLanguages}) {
  const langRef = useRef()
  const scoreRef = useRef()

  function handleChange(e) {
    setLanguages([
      ...languages,
      {
        languageName: langRef.current.value,
        scoreOutof100: scoreRef.current.value
      },
    ]);
    
  }
  const styles = {
    display: "flex",
    alignItems: "center",
    gap: "10px",

  }

  return (
    <div style={styles} className="lang-inputs">
      <p>
        <label htmlFor="languageName">Language</label>
        <input
          placeholder="Enter language... "
          className="languages"
          name="languageName"
          type="text"
          required={scoreRef.current?.value}
          ref={langRef}
          />
      </p>

      <p>
        <label htmlFor="scoreOutof100">Score</label>
        <input
          className="scores"
          name="scoreOutof100"
          placeholder="Enter Score out of 100... "
          type="number"
          max={100}
          onBlur={handleChange}
          ref={scoreRef}
          />
      </p>
    </div>
  );
}
