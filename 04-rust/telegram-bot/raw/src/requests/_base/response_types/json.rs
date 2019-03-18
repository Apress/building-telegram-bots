use serde::de::DeserializeOwned;
use serde_json;

use requests::*;
use types::*;

pub trait JsonResponse {
    type Raw;
    type Type;

    fn map(raw: Self::Raw) -> Self::Type;
}

pub struct JsonIdResponse<Type> {
    phantom: ::std::marker::PhantomData<Type>,
}

impl<Type> JsonResponse for JsonIdResponse<Type> {
    type Raw = Type;
    type Type = Type;

    fn map(raw: Self::Raw) -> Self::Type {
        raw
    }
}

pub struct JsonTrueToUnitResponse;

impl JsonResponse for JsonTrueToUnitResponse {
    type Raw = True;
    type Type = ();

    fn map(_: Self::Raw) -> Self::Type {
        ()
    }
}

impl<Resp: JsonResponse> ResponseType for Resp where <Resp as JsonResponse>::Raw: DeserializeOwned {
    type Type = <Resp as JsonResponse>::Type;

    fn deserialize(resp: HttpResponse) -> Result<Self::Type, Error> {
        if let Some(body) = resp.body.as_ref() {
            let raw = serde_json::from_slice(body)?;
            match raw {
                ResponseWrapper::Success {result} => {
                    Ok(<Self as JsonResponse>::map(result))
                },
                ResponseWrapper::Error { description, parameters } => {
                    Err(ErrorKind::TelegramError {
                        description: description,
                        parameters: parameters
                    }.into())
                },
            }
        } else {
            Err(ErrorKind::EmptyBody.into())
        }
    }
}
