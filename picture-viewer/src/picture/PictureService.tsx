import {Picture} from "./Picture";
import {PictureDirectory} from "./PictureDirectory";

export class PictureService {

    getPictures(dir: PictureDirectory): Promise<Picture[]> {
        return fetch(`${process.env.REACT_APP_SERVER_URL}` + "/media/pictures?dir=" + dir.id)
            .then(res => res.json())
            .then(res => res as Picture[])
    }

}