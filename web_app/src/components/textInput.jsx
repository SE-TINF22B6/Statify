import "../css/text-input.css"
export default function TextInput({name, type}){


    return (
        <div className="text-input">
            <div className="name">
                {name}
            </div>
            <div className="input-field">
                <input type={type} placeholder={name}/>
            </div>
        </div>
    )
}