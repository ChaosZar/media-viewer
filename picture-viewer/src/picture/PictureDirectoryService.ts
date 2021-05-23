import {PictureDirectory} from "./PictureDirectory";

export class PictureDirectoryService {

    getDirectories(): Promise<PictureDirectory[]> {
        return fetch(`${process.env.REACT_APP_SERVER_URL}` + "/media/pictures/directories")
            // the JSON body is taken from the response
            .then(res => {
                return res.json()
            })
            .then(res => {
                return res as PictureDirectory[]
            })
    }

}